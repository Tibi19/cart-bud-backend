package com.tam.data.repository.contract

import com.tam.data.model.request.SendInvitationRequest
import com.tam.data.model.response.InvitationResponse

interface InvitationRepository {
    fun createInvitation(fromAdminId: String, sendInvitationRequest: SendInvitationRequest): Boolean
    fun getUserInvitations(userId: String): List<InvitationResponse>?
    fun acceptInvitation(toUserId: String, onGroupId: String): Boolean
    fun deleteInvitation(toUserId: String, onGroupId: String): Boolean
}