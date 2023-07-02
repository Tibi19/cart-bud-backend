package com.tam.routing

import com.tam.data.model.request.ChangeRequest
import com.tam.data.model.request.ChangesRequest
import com.tam.data.model.request.ChangingType
import com.tam.data.model.response.ChangesResponse
import com.tam.data.repository.contract.EntryRepository
import com.tam.data.repository.contract.GroupRepository
import com.tam.data.repository.contract.ShoppingListRepository
import com.tam.routing.util.receiveRequestInfoWithErrorHandle
import com.tam.usecase.Changes
import com.tam.usecase.doChangesExist
import com.tam.usecase.getChanges
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ROUTE_CHANGES = "changes"

fun Route.changes() {
    val groupRepository by inject<GroupRepository>()
    val shoppingListRepository by inject<ShoppingListRepository>()
    val entryRepository by inject<EntryRepository>()

    authenticate {
        get(ROUTE_CHANGES) {
            val (userId, changesRequest) = call.receiveRequestInfoWithErrorHandle<ChangesRequest> { statusCode ->
                call.respond(statusCode)
            } ?: return@get

            val typeToChangeRequests = changesRequest.changes.groupBy { it.type }

            val groupsChanges = typeToChangeRequests.toChangesOfType(
                changingType = ChangingType.Group,
                getByIds = { ids ->
                    groupRepository.getGroupsByIds(userId, ids)
                }
            ) ?: run {
                call.respond(HttpStatusCode.InternalServerError)
                return@get
            }

            val shoppingListsChanges = typeToChangeRequests.toChangesOfType(
                changingType = ChangingType.ShoppingList,
                getByIds = shoppingListRepository::getShoppingListsByIds
            ) ?: run {
                call.respond(HttpStatusCode.InternalServerError)
                return@get
            }

            val entriesChanges = typeToChangeRequests.toChangesOfType(
                changingType = ChangingType.Entry,
                getByIds = entryRepository::getEntriesByIds
            ) ?: run {
                call.respond(HttpStatusCode.InternalServerError)
                return@get
            }

            val changesResponse = ChangesResponse(
                changesExist = doChangesExist(groupsChanges, shoppingListsChanges, entriesChanges),
                groups = groupsChanges.updated,
                shoppingLists = shoppingListsChanges.updated,
                entries = entriesChanges.updated,
                deletedIds = groupsChanges.deletedIds + shoppingListsChanges.deletedIds + entriesChanges.deletedIds
            )

            call.respond(HttpStatusCode.OK, changesResponse)
        }
    }

}

private inline fun <reified T> Map<ChangingType, List<ChangeRequest>>.toChangesOfType(
    changingType: ChangingType,
    getByIds: (ids: List<String>) -> List<T>?
): Changes<T>? =
    get(changingType)
        ?.let { changeRequests ->
            getChanges(
                changeRequests = changeRequests,
                changingType = changingType,
                getByIds = getByIds
            ) ?: return null
        } ?: Changes.empty()