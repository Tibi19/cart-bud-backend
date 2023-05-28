package com.tam.data.dao

import com.tam.data.dao.contract.entity.InvitationDao
import com.tam.data.model.entity.Invitation

class InvitationDaoImpl : InvitationDao {
    override fun insert(element: Invitation): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(userId: String, groupId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getInvitationsOfUserId(userId: String): List<Invitation>? {
        TODO("Not yet implemented")
    }
}