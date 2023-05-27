package com.tam.routing

import com.tam.data.model.request.GroupRequest
import com.tam.data.repository.Repository
import com.tam.routing.util.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ROUTE_GROUP_ROOT = "group"
const val ROUTE_GROUP_CREATE = "$ROUTE_GROUP_ROOT/create"
const val ROUTE_GROUP_USER_GROUPS = "$ROUTE_GROUP_ROOT/user/groups"
const val ROUTE_GROUP_UPDATE = "$ROUTE_GROUP_ROOT/update"
const val ROUTE_GROUP_DELETE = "$ROUTE_GROUP_ROOT/delete"

fun Route.createGroup() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_GROUP_CREATE) {
            val (userId, group) = call.receiveRequestInfoWithErrorHandle<GroupRequest> { statusCode ->
                call.respond(statusCode)
            } ?: return@post

            val isOk = repository.createGroup(group, userId)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.userGroups() {
    val repository by inject<Repository>()

    authenticate {
        get(ROUTE_GROUP_USER_GROUPS) {
            val userId = call.receiveUserIdOrNull()
                ?: run {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }

            val userGroups = repository.getGroupsByUserId(userId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, userGroups)
        }
    }

}

fun Route.updateGroup() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_GROUP_UPDATE) {
            val (userId, group) = call.receiveRequestInfoWithErrorHandle<GroupRequest> { statusCode ->
                call.respond(statusCode)
            } ?: return@post

            val isGroupAdmin = repository.isGroupAdmin(group.id, userId)
            if (!isGroupAdmin) {
                call.respond(HttpStatusCode.Forbidden)
                return@post
            }

            val isOk = repository.updateGroup(group)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.deleteGroup() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_GROUP_DELETE) {
            val (userId, group) = call.receiveRequestInfoWithErrorHandle<GroupRequest> { statusCode ->
                    call.respond(statusCode)
                } ?: return@post

            val isGroupAdmin = repository.isGroupAdmin(group.id, userId)
            if (!isGroupAdmin) {
                call.respond(HttpStatusCode.Forbidden)
                return@post
            }

            val isOk = repository.deleteGroup(group)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}
