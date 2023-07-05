package com.tam.routing

import com.tam.data.model.request.EntryRequest
import com.tam.data.model.request.ShoppingListEntriesRequest
import com.tam.data.repository.contract.EntryRepository
import com.tam.routing.util.receiveRequestOrNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val ROUTE_ENTRY_ROOT = "entry"
private const val ROUTE_ENTRY_CREATE = "$ROUTE_ENTRY_ROOT/create"
private const val ROUTE_ENTRY_LIST_ENTRIES = "$ROUTE_ENTRY_ROOT/list/entries"
private const val ROUTE_ENTRY_UPDATE = "$ROUTE_ENTRY_ROOT/update"
private const val ROUTE_ENTRY_DELETE = "$ROUTE_ENTRY_ROOT/delete"

fun Route.createEntry() {
    val entryRepository by inject<EntryRepository>()

    authenticate {
        post(ROUTE_ENTRY_CREATE) {
            val entryRequest = call.receiveRequestOrNull<EntryRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = entryRepository.createEntry(entryRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.shoppingListEntries() {
    val entryRepository by inject<EntryRepository>()

    authenticate {
        get(ROUTE_ENTRY_LIST_ENTRIES) {
            val shoppingListEntriesRequest = call.receiveRequestOrNull<ShoppingListEntriesRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

            val shoppingListEntries = entryRepository.getEntriesByParentId(shoppingListEntriesRequest.parentListId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, shoppingListEntries)
        }
    }

}

fun Route.updateEntry() {
    val entryRepository by inject<EntryRepository>()

    authenticate {
        post(ROUTE_ENTRY_UPDATE) {
            val entryRequest = call.receiveRequestOrNull<EntryRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = entryRepository.updateEntry(entryRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.deleteEntry() {
    val entryRepository by inject<EntryRepository>()

    authenticate {
        post(ROUTE_ENTRY_DELETE) {
            val entryRequest = call.receiveRequestOrNull<EntryRequest>()
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val isOk = entryRepository.deleteEntry(entryRequest)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}