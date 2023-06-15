package com.tam.data.dao

import com.tam.data.dao.contract.entity.MemberDao
import com.tam.data.model.entity.Group
import com.tam.data.model.entity.Member
import com.tam.data.table.members
import com.tam.data.util.tryAdd
import com.tam.data.util.tryDeleteIf
import com.tam.data.util.tryGet
import com.tam.data.util.tryRemoveIf
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.map

class MemberDaoImpl(database: Database) : MemberDao {

    private val members = database.members

    override fun insert(element: Member): Boolean =
        members.tryAdd(element)

    override fun delete(userId: String, groupId: String): Boolean =
        members.tryDeleteIf {
            it.user.id eq userId and (it.group.id eq groupId)
        }

    override fun getGroupsOfMemberId(memberId: String): List<Group>? =
        tryGet {
            members
                .filter { it.user.id eq memberId }
                .map { it.group }
        }

}