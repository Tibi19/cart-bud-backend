package com.tam.data.repository

import com.tam.data.model.request.SendInvitationRequest
import com.tam.data.model.response.InvitationResponse
import com.tam.data.repository.contract.InvitationRepository

class InvitationRepositoryImpl : InvitationRepository {
    override fun createInvitation(fromAdminId: String, sendInvitationRequest: SendInvitationRequest): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUserInvitations(userId: String): List<InvitationResponse>? {
        TODO("Not yet implemented")
    }

    override fun acceptInvitation(toUserId: String, onGroupId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteInvitation(toUserId: String, onGroupId: String): Boolean {
        TODO("Not yet implemented")
    }
}