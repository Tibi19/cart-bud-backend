package com.tam.routing

import com.tam.data.model.request.AcceptInvitationRequest
import com.tam.data.model.request.SendInvitationRequest
import com.tam.data.repository.Repository
import com.tam.routing.util.receiveRequestInfoWithErrorHandle
import com.tam.routing.util.receiveUserIdOrNull
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ROUTE_INVITATION_ROOT = "invitation"
const val ROUTE_INVITATION_SEND = "$ROUTE_INVITATION_ROOT/send"
const val ROUTE_INVITATION_USER_INVITATIONS = "$ROUTE_INVITATION_ROOT/user/invitations"
const val ROUTE_INVITATION_ACCEPT = "$ROUTE_INVITATION_ROOT/accept"

fun Route.sendInvitation() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_INVITATION_SEND) {
            val (userId, invitation) = call.receiveRequestInfoWithErrorHandle<SendInvitationRequest> { statusCode ->
                call.respond(statusCode)
            } ?: return@post

            val isOk = repository.createInvitation(userId, invitation)
            if (!isOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}

fun Route.getUserInvitations() {
    val repository by inject<Repository>()

    authenticate {
        get(ROUTE_INVITATION_USER_INVITATIONS) {
            val userId = call.receiveUserIdOrNull()
                ?: run {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }

            val userInvitations = repository.getUserInvitations(userId)
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(HttpStatusCode.OK, userInvitations)
        }
    }

}

fun Route.acceptInvitation() {
    val repository by inject<Repository>()

    authenticate {
        post(ROUTE_INVITATION_ACCEPT) {
            val (userId, invitation) = call.receiveRequestInfoWithErrorHandle<AcceptInvitationRequest> {
                call.respond(HttpStatusCode.Unauthorized)
            } ?: return@post

            val isAcceptOk = repository.acceptInvitation(userId, invitation.onGroupId)
            if (!isAcceptOk) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            val isDeleteOk = repository.deleteInvitation(userId, invitation.onGroupId)
            if (!isDeleteOk) {
                repository.deleteGroupMember(userId, invitation.onGroupId)
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }
    }
}