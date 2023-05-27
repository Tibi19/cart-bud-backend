package com.tam.routing

import com.tam.data.model.request.ParentListsRequest
import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.repository.Repository
import com.tam.routing.util.receiveRequestOrNull
import com.tam.usecase.validateParent
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ROUTE_LIST_ROOT = "list"
const val ROUTE_LIST_CREATE = "$ROUTE_LIST_ROOT/create"
const val ROUTE_LIST_PARENT_LISTS = "$ROUTE_LIST_ROOT/parent/lists"
const val ROUTE_LIST_UPDATE = "$ROUTE_LIST_ROOT/update"
const val ROUTE_LIST_DELETE = "$ROUTE_LIST_ROOT/delete"

fun Route.createShoppingList() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_LIST_CREATE) {
            val shoppingListRequest = call.receiveShoppingListRequestAndValidate(repository)
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = repository.createShoppingList(shoppingListRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

private suspend fun ApplicationCall.receiveShoppingListRequestAndValidate(
    repository: Repository
): ShoppingListRequest? {
    val shoppingListRequest = receiveRequestOrNull<ShoppingListRequest>() ?: return null
    val isValidParent = shoppingListRequest.validateParent(repository)
    if (!isValidParent) {
        return null
    }
    return shoppingListRequest
}

fun Route.parentShoppingLists() {
    val repository by inject<Repository>()

    authenticate {
        get(ROUTE_LIST_PARENT_LISTS) {
            val parentListsRequest = call.receiveRequestOrNull<ParentListsRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

            val shoppingLists = repository.getShoppingListsByParentId(parentListsRequest.parentId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, shoppingLists)
        }
    }

}

fun Route.updateShoppingList() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_LIST_UPDATE) {
            val shoppingListRequest = call.receiveShoppingListRequestAndValidate(repository)
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = repository.updateShoppingList(shoppingListRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.deleteShoppingList() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_LIST_DELETE) {
            val shoppingListRequest = call.receiveShoppingListRequestAndValidate(repository)
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = repository.deleteShoppingList(shoppingListRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}