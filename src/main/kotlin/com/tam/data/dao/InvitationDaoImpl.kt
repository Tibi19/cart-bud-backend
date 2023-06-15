package com.tam.data.dao

import com.tam.data.dao.contract.entity.InvitationDao
import com.tam.data.model.entity.Invitation
import com.tam.data.table.invitations
import com.tam.data.util.tryAdd
import com.tam.data.util.tryDeleteIf
import com.tam.data.util.tryGet
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.toList

class InvitationDaoImpl(database: Database) : InvitationDao {

    private val invitations = database.invitations

    override fun insert(element: Invitation): Boolean =
        invitations.tryAdd(element)

    override fun delete(userId: String, groupId: String): Boolean =
        invitations.tryDeleteIf {
            it.user.id eq userId and (it.group.id eq groupId)
        }

    override fun getInvitationsOfUserId(userId: String): List<Invitation>? =
        tryGet {
            invitations
                .filter { it.user.id eq userId }
                .toList()
        }

}