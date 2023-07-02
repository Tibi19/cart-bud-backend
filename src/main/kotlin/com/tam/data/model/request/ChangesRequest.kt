package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ChangesRequest(
    val changes: List<ChangeRequest>
)

@Serializable
data class ChangeRequest(
    val id: String,
    val type: ChangingType,
    val timestampOfLastChange: Long
)

@Serializable
enum class ChangingType {
    Group,
    ShoppingList,
    Entry
}
