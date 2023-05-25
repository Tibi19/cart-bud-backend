package com.tam.data.dao.contract.entity

import com.tam.data.dao.contract.operation.InsertDao
import com.tam.data.model.entity.Group
import com.tam.data.model.entity.Member

interface MemberDao : InsertDao<Member> {
    fun getGroupsOfMemberId(memberId: String): List<Group>?
    fun delete(userId: String, groupId: String): Boolean
}