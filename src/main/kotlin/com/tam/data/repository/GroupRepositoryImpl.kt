package com.tam.data.repository

import com.tam.data.dao.contract.entity.GroupDao
import com.tam.data.dao.contract.entity.MemberDao
import com.tam.data.dao.contract.entity.UserDao
import com.tam.data.mapper.toGroup
import com.tam.data.mapper.toGroupResponse
import com.tam.data.model.entity.Group
import com.tam.data.model.request.GroupRequest
import com.tam.data.model.response.GroupResponse
import com.tam.data.repository.contract.GroupRepository

class GroupRepositoryImpl(
    private val groupDao: GroupDao,
    private val memberDao: MemberDao,
    private val userDao: UserDao
) : GroupRepository {

    override fun createGroup(groupRequest: GroupRequest, userId: String): Boolean {
        val admin = userDao.getById(userId) ?: return false
        val newGroup = Group.create(
            id = groupRequest.id,
            admin = admin,
            name = groupRequest.name
        )
        return groupDao.insert(newGroup)
    }

    override fun getGroupsByUserId(userId: String): List<GroupResponse>? =
        memberDao
            .getGroupsOfMemberId(userId)
            ?.map { group ->
                group.toGroupResponse(userId)
            }

    override fun updateGroup(groupRequest: GroupRequest, adminId: String): Boolean {
        val admin = userDao.getById(adminId) ?: return false
        return groupDao.update(groupRequest.toGroup(admin))
    }

    override fun deleteGroupMember(userId: String, groupId: String): Boolean =
        memberDao.delete(userId, groupId)

    override fun deleteGroup(groupRequest: GroupRequest): Boolean =
        groupDao.delete(groupRequest.id)

    override fun getGroupById(groupId: String): Group? =
        groupDao.getById(groupId)

}