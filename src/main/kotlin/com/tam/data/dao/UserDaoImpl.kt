package com.tam.data.dao

import com.tam.data.dao.contract.entity.UserDao
import com.tam.data.model.entity.User
import com.tam.data.table.users
import com.tam.data.util.tryAdd
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find

class UserDaoImpl(private val database: Database) : UserDao {
    override fun getUserByUsername(username: String): User? =
        database.users.find { it.username eq username }

    override fun insert(element: User): Boolean =
        database.users.tryAdd(element)
}