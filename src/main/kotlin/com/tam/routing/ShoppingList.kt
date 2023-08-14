package com.tam.routing

import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.repository.contract.GroupRepository
import com.tam.data.repository.contract.ShoppingListRepository
import com.tam.routing.util.receiveRequestOrNull
import com.tam.routing.util.receiveUserIdOrNull
import com.tam.usecase.validateGroupParent
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val ARG_GROUP_ID = "group_id"

private const val ROUTE_LIST_ROOT = "list"
private const val ROUTE_LIST_CREATE = "$ROUTE_LIST_ROOT/create"
private const val ROUTE_LIST_USER_LISTS = "$ROUTE_LIST_ROOT/user/lists"
private const val ROUTE_LIST_GROUP_LISTS = "$ROUTE_LIST_ROOT/group/{$ARG_GROUP_ID}/lists"
private const val ROUTE_LIST_UPDATE = "$ROUTE_LIST_ROOT/update"
private const val ROUTE_LIST_DELETE = "$ROUTE_LIST_ROOT/delete"

fun Route.createShoppingList() {
    val shoppingListRepository by inject<ShoppingListRepository>()

    authenticate {
        post(ROUTE_LIST_CREATE) {
            val shoppingListRequest = call.receiveOnCreateShoppingListRequestAndValidate()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = shoppingListRepository.createShoppingList(shoppingListRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

private suspend fun ApplicationCall.receiveShoppingListRequestAndValidate(): ShoppingListRequest? {
    val userId = receiveUserIdOrNull() ?: return null
    val shoppingListRequest = receiveRequestOrNull<ShoppingListRequest>() ?: return null

    if (!shoppingListRequest.hasGroupParent) {
        return shoppingListRequest
    }

    val groupRepository by inject<GroupRepository>()
    val isValidGroupParent = shoppingListRequest.validateGroupParent(userId, groupRepository)
    if (!isValidGroupParent) {
        return null
    }
    return shoppingListRequest
}

private suspend fun ApplicationCall.receiveOnCreateShoppingListRequestAndValidate(): ShoppingListRequest? {
    val userId = receiveUserIdOrNull() ?: return null
    val shoppingListRequest = receiveRequestOrNull<ShoppingListRequest>() ?: return null

    if (!shoppingListRequest.hasGroupParent) {
        return shoppingListRequest.copy(parentId = userId)
    }

    val groupRepository by inject<GroupRepository>()
    val isValidGroupParent = shoppingListRequest.validateGroupParent(userId, groupRepository)
    if (!isValidGroupParent) {
        return null
    }
    return shoppingListRequest
}

fun Route.userShoppingLists() {
    val shoppingListRepository by inject<ShoppingListRepository>()

    authenticate {
        get(ROUTE_LIST_USER_LISTS) {
            val userId = call.receiveUserIdOrNull()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

            val shoppingLists = shoppingListRepository.getShoppingListsByParentId(userId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, shoppingLists)
        }
    }

}

fun Route.groupShoppingLists() {
    val shoppingListRepository by inject<ShoppingListRepository>()

    authenticate {
        get(ROUTE_LIST_GROUP_LISTS) {
            val groupId = call.parameters[ARG_GROUP_ID]
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

            val shoppingLists = shoppingListRepository.getShoppingListsByParentId(groupId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, shoppingLists)
        }
    }

}

fun Route.updateShoppingList() {
    val shoppingListRepository by inject<ShoppingListRepository>()

    authenticate {
        post(ROUTE_LIST_UPDATE) {
            val shoppingListRequest = call.receiveShoppingListRequestAndValidate()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = shoppingListRepository.updateShoppingList(shoppingListRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.deleteShoppingList() {
    val shoppingListRepository by inject<ShoppingListRepository>()

    authenticate {
        post(ROUTE_LIST_DELETE) {
            val shoppingListRequest = call.receiveShoppingListRequestAndValidate()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = shoppingListRepository.deleteShoppingList(shoppingListRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}