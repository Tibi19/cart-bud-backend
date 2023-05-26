package com.tam.data.repository

import com.tam.data.model.entity.User
import com.tam.data.model.request.GroupRequest
import com.tam.data.model.response.GroupResponse
import com.tam.security.hashing.SaltedHash

interface Repository {

    fun createUser(username: String, saltedHash: SaltedHash): Boolean
    fun getUserByUsername(username: String): User?
    fun createGroup(groupRequest: GroupRequest, userId: String): Boolean
    fun getGroupsByUserId(userId: String): List<GroupResponse>?
    fun isGroupAdmin(groupId: String, userId: String): Boolean
    fun updateGroup(groupRequest: GroupRequest): Boolean
    fun deleteGroup(groupRequest: GroupRequest): Boolean

}