package com.tam.routing

import com.tam.data.model.request.GroupRequest
import com.tam.data.repository.contract.GroupRepository
import com.tam.routing.util.receiveRequestInfoWithErrorHandle
import com.tam.routing.util.receiveUserIdOrNull
import com.tam.usecase.isGroupAdmin
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val ROUTE_GROUP_ROOT = "group"
private const val ROUTE_GROUP_CREATE = "$ROUTE_GROUP_ROOT/create"
private const val ROUTE_GROUP_USER_GROUPS = "$ROUTE_GROUP_ROOT/user/groups"
private const val ROUTE_GROUP_UPDATE = "$ROUTE_GROUP_ROOT/update"
private const val ROUTE_GROUP_DELETE = "$ROUTE_GROUP_ROOT/delete"

fun Route.createGroup() {
    val groupRepository by inject<GroupRepository>()

    authenticate {
        post(ROUTE_GROUP_CREATE) {
            val (userId, group) = call.receiveRequestInfoWithErrorHandle<GroupRequest> { statusCode ->
                call.respond(statusCode)
            } ?: return@post

            val isCreateOk = groupRepository.createGroup(group, userId)
            if (!isCreateOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            val isAddMemberOk = groupRepository.createGroupMember(userId, group.id)
            if (!isAddMemberOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.userGroups() {
    val groupRepository by inject<GroupRepository>()

    authenticate {
        get(ROUTE_GROUP_USER_GROUPS) {
            val userId = call.receiveUserIdOrNull()
                ?: run {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }

            val userGroups = groupRepository.getGroupsByUserId(userId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, userGroups)
        }
    }

}

fun Route.updateGroup() {
    val groupRepository by inject<GroupRepository>()

    authenticate {
        post(ROUTE_GROUP_UPDATE) {
            val (userId, group) = call.receiveRequestInfoWithErrorHandle<GroupRequest> { statusCode ->
                call.respond(statusCode)
            } ?: return@post

            val isGroupAdmin = isGroupAdmin(groupRepository, group.id, userId)
            if (!isGroupAdmin) {
                call.respond(HttpStatusCode.Forbidden)
                return@post
            }

            val isOk = groupRepository.updateGroup(group, userId)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.deleteGroup() {
    val groupRepository by inject<GroupRepository>()

    authenticate {
        post(ROUTE_GROUP_DELETE) {
            val (userId, group) = call.receiveRequestInfoWithErrorHandle<GroupRequest> { statusCode ->
                    call.respond(statusCode)
                } ?: return@post

            val isGroupAdmin = isGroupAdmin(groupRepository, group.id, userId)
            if (!isGroupAdmin) {
                call.respond(HttpStatusCode.Forbidden)
                return@post
            }

            val isOk = groupRepository.deleteGroup(group)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}
