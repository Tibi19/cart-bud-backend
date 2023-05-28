package com.tam.data.repository.contract

import com.tam.data.model.entity.User
import com.tam.security.hashing.SaltedHash

interface UserRepository {
    fun createUser(username: String, saltedHash: SaltedHash): Boolean
    fun getUserByUsername(username: String): User?
    fun getUserByUserId(userId: String): User?
}