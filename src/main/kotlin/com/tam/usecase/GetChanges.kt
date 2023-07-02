package com.tam.usecase

import com.tam.data.model.request.ChangeRequest
import com.tam.data.model.request.ChangingType
import com.tam.data.model.response.EntryResponse
import com.tam.data.model.response.GroupResponse
import com.tam.data.model.response.ShoppingListResponse

data class Changes<T>(
    val updated: List<T>,
    val deletedIds: List<String>
) {
    companion object {
        fun <T> empty() = Changes(
            updated = emptyList<T>(),
            deletedIds = emptyList()
        )
    }
}

@PublishedApi
internal data class ChangingData(
    val id: String,
    val timestampOfLastChange: Long
)

private const val NAMING_RESPONSE = "Response"

@PublishedApi
internal val ChangingType.responseName get() = "$name$NAMING_RESPONSE"

inline fun <reified T> getChanges(
    changeRequests: List<ChangeRequest>,
    changingType: ChangingType,
    getByIds: (ids: List<String>) -> List<T>?
): Changes<T>? {
    if (T::class.java.name != changingType.responseName) return null

    val ids = changeRequests.map { it.id }
    val elements = getByIds(ids) ?: return null
    val changingDataToElementMap = getElementsByChangingData(changingType, elements)
    val changingDatas = changingDataToElementMap.keys

    val changingDataIds = changingDatas.map { it.id }
    val deletedIds = ids - changingDataIds.toSet()

    val updatedDatas = changingDatas.filter { changingData ->
        val changeRequest = changeRequests
            .find { changeRequest ->
                changeRequest.id == changingData.id
            } ?: return@filter false
        changingData.timestampOfLastChange > changeRequest.timestampOfLastChange
    }
    val updatedElements = updatedDatas.map { changingData ->
        changingDataToElementMap[changingData]!!
    }

    return Changes(
        updated = updatedElements,
        deletedIds = deletedIds
    )
}

@PublishedApi
internal fun <T> getElementsByChangingData(
    changingType: ChangingType,
    elements: List<T>
): Map<ChangingData, T> =
    when (changingType) {
        ChangingType.Group -> elements
            .associateBy { element ->
                (element as GroupResponse).run {
                    ChangingData(
                        id = id,
                        timestampOfLastChange = timestampOfLastChange
                    )
                }
            }
        ChangingType.ShoppingList -> elements
            .associateBy { element ->
                (element as ShoppingListResponse).run {
                    ChangingData(
                        id = id,
                        timestampOfLastChange = timestampOfLastChange
                    )
                }
            }
        ChangingType.Entry -> elements
            .associateBy { element ->
                (element as EntryResponse).run {
                    ChangingData(
                        id = id,
                        timestampOfLastChange = timestampOfLastChange
                    )
                }
            }
    }
