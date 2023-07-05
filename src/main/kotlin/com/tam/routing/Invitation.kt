package com.tam.routing

import com.tam.data.model.request.AcceptInvitationRequest
import com.tam.data.model.request.SendInvitationRequest
import com.tam.data.repository.contract.GroupRepository
import com.tam.data.repository.contract.InvitationRepository
import com.tam.routing.util.receiveRequestInfoWithErrorHandle
import com.tam.routing.util.receiveUserIdOrNull
import com.tam.usecase.hasInvitation
import com.tam.usecase.isGroupAdmin
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val ROUTE_INVITATION_ROOT = "invitation"
private const val ROUTE_INVITATION_SEND = "$ROUTE_INVITATION_ROOT/send"
private const val ROUTE_INVITATION_USER_INVITATIONS = "$ROUTE_INVITATION_ROOT/user/invitations"
private const val ROUTE_INVITATION_ACCEPT = "$ROUTE_INVITATION_ROOT/accept"

fun Route.sendInvitation() {
    val invitationRepository by inject<InvitationRepository>()
    val groupRepository by inject<GroupRepository>()

    authenticate {
        post(ROUTE_INVITATION_SEND) {
            val (userId, invitation) = call.receiveRequestInfoWithErrorHandle<SendInvitationRequest> { statusCode ->
                call.respond(statusCode)
            } ?: return@post

            val isGroupAdmin = isGroupAdmin(groupRepository, invitation.onGroupId, userId)
            if (!isGroupAdmin) {
                call.respond(HttpStatusCode.Forbidden)
                return@post
            }

            val isOk = invitationRepository.createInvitation(userId, invitation)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.getUserInvitations() {
    val invitationRepository by inject<InvitationRepository>()

    authenticate {
        get(ROUTE_INVITATION_USER_INVITATIONS) {
            val userId = call.receiveUserIdOrNull()
                ?: run {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }

            val userInvitations = invitationRepository.getUserInvitations(userId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, userInvitations)
        }
    }

}

fun Route.acceptInvitation() {
    val invitationRepository by inject<InvitationRepository>()
    val groupRepository by inject<GroupRepository>()

    authenticate {
        post(ROUTE_INVITATION_ACCEPT) {
            val (userId, invitation) = call.receiveRequestInfoWithErrorHandle<AcceptInvitationRequest> {
                call.respond(HttpStatusCode.Unauthorized)
            } ?: return@post

            val hasInvitation = hasInvitation(invitationRepository, invitation.onGroupId, userId)
            if (!hasInvitation) {
                call.respond(HttpStatusCode.Forbidden)
                return@post
            }

            val isAcceptOk = invitationRepository.acceptInvitation(userId, invitation.onGroupId)
            if (!isAcceptOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            val isDeleteOk = invitationRepository.deleteInvitation(userId, invitation.onGroupId)
            if (!isDeleteOk) {
                groupRepository.deleteGroupMember(userId, invitation.onGroupId)
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}