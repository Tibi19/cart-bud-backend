package com.tam.data.repository

import com.tam.data.model.entity.User
import com.tam.security.hashing.SaltedHash

interface Repository {

    fun createUser(username: String, saltedHash: SaltedHash): Boolean
    fun getUserByUsername(username: String): User?

}