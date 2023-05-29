package com.tam.data.repository.contract

import com.tam.data.model.entity.Group
import com.tam.data.model.request.GroupRequest
import com.tam.data.model.response.GroupResponse

interface GroupRepository {
    fun createGroup(groupRequest: GroupRequest, userId: String): Boolean
    fun createGroupMember(userId: String, groupId: String): Boolean
    fun getGroupsByUserId(userId: String): List<GroupResponse>?
    fun updateGroup(groupRequest: GroupRequest, adminId: String): Boolean
    fun deleteGroupMember(userId: String, groupId: String): Boolean
    fun deleteGroup(groupRequest: GroupRequest): Boolean
    fun getGroupById(groupId: String): Group?
}