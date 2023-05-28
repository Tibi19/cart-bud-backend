package com.tam.data.repository

import com.tam.data.dao.contract.entity.UserDao
import com.tam.data.model.entity.User
import com.tam.data.repository.contract.UserRepository
import com.tam.security.hashing.SaltedHash
import java.util.UUID

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun createUser(username: String, saltedHash: SaltedHash): Boolean {
        val newUser = User.create(
            id = UUID.randomUUID().toString(),
            username = username,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        return userDao.insert(newUser)
    }

    override fun getUserByUsername(username: String): User? =
        userDao.getUserByUsername(username)

    override fun getUserByUserId(userId: String): User? =
        userDao.getById(userId)

}