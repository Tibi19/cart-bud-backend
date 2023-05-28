package com.tam.data.dao

import com.tam.data.dao.contract.entity.UserDao
import com.tam.data.model.entity.User
import com.tam.data.table.users
import com.tam.data.util.tryAdd
import com.tam.data.util.tryGet
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find

class UserDaoImpl(database: Database) : UserDao {

    private val users = database.users

    override fun getUserByUsername(username: String): User? =
        tryGet {
            users.find { it.username eq username }
        }

    override fun insert(element: User): Boolean =
        users.tryAdd(element)

    override fun getById(id: String): User? =
        tryGet {
            users.find { it.id eq id }
        }

}