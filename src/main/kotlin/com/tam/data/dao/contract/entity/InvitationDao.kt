package com.tam.data.dao.contract.entity

import com.tam.data.dao.contract.operation.InsertDao
import com.tam.data.model.entity.Invitation

interface InvitationDao : InsertDao<Invitation> {
    fun getInvitationsOfUserId(userId: String): List<Invitation>?
    fun delete(userId: String, groupId: String): Boolean
}