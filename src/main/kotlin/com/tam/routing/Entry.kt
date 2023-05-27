package com.tam.routing

import com.tam.data.model.request.EntryRequest
import com.tam.data.model.request.ShoppingListEntriesRequest
import com.tam.data.repository.Repository
import com.tam.routing.util.receiveRequestOrNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ROUTE_ENTRY_ROOT = "entry"
const val ROUTE_ENTRY_CREATE = "$ROUTE_ENTRY_ROOT/create"
const val ROUTE_ENTRY_LIST_ENTRIES = "$ROUTE_ENTRY_ROOT/list/entries"
const val ROUTE_ENTRY_UPDATE = "$ROUTE_ENTRY_ROOT/update"
const val ROUTE_ENTRY_DELETE = "$ROUTE_ENTRY_ROOT/delete"

fun Route.createEntry() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_ENTRY_CREATE) {
            val entryRequest = call.receiveRequestOrNull<EntryRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = repository.createEntry(entryRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.shoppingListEntries() {
    val repository by inject<Repository>()

    authenticate {
        get(ROUTE_ENTRY_LIST_ENTRIES) {
            val shoppingListEntriesRequest = call.receiveRequestOrNull<ShoppingListEntriesRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

            val shoppingListEntries = repository.getEntriesByParentId(shoppingListEntriesRequest.parentListId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, shoppingListEntries)
        }
    }

}

fun Route.updateEntry() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_ENTRY_UPDATE) {
            val entryRequest = call.receiveRequestOrNull<EntryRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = repository.updateEntry(entryRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.deleteEntry() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_ENTRY_DELETE) {
            val entryRequest = call.receiveRequestOrNull<EntryRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = repository.deleteEntry(entryRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}