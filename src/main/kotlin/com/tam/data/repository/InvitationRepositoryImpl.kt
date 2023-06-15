package com.tam.data.repository

import com.tam.data.dao.contract.entity.GroupDao
import com.tam.data.dao.contract.entity.InvitationDao
import com.tam.data.dao.contract.entity.MemberDao
import com.tam.data.dao.contract.entity.UserDao
import com.tam.data.mapper.toInvitationResponse
import com.tam.data.model.entity.Invitation
import com.tam.data.model.entity.Member
import com.tam.data.model.request.SendInvitationRequest
import com.tam.data.model.response.InvitationResponse
import com.tam.data.repository.contract.InvitationRepository

class InvitationRepositoryImpl(
    private val invitationDao: InvitationDao,
    private val userDao: UserDao,
    private val groupDao: GroupDao,
    private val memberDao: MemberDao
) : InvitationRepository {

    override fun createInvitation(fromAdminId: String, sendInvitationRequest: SendInvitationRequest): Boolean {
        val toUser = userDao.getUserByUsername(sendInvitationRequest.toUsername) ?: return false
        val onGroup = groupDao.getById(sendInvitationRequest.onGroupId) ?: return false
        val invitation = Invitation.create(
            user = toUser,
            group = onGroup,
            adminId = fromAdminId
        )
        return invitationDao.insert(invitation)
    }

    override fun getUserInvitations(userId: String): List<InvitationResponse>? =
        invitationDao
            .getInvitationsOfUserId(userId)
            ?.map { invitation ->
                val admin = userDao.getById(invitation.adminId) ?: return null
                invitation.toInvitationResponse(admin)
            }

    override fun acceptInvitation(toUserId: String, onGroupId: String): Boolean {
        val toUser = userDao.getById(toUserId) ?: return false
        val onGroup = groupDao.getById(onGroupId) ?: return false
        val newGroupMember = Member.create(
            user = toUser,
            group = onGroup
        )
        return memberDao.insert(newGroupMember)
    }

    override fun deleteInvitation(toUserId: String, onGroupId: String): Boolean =
        invitationDao.delete(toUserId, onGroupId)

}