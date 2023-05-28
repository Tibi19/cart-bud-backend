package com.tam.data.dao.contract.entity

import com.tam.data.dao.contract.operation.GetByIdDao
import com.tam.data.dao.contract.operation.InsertDao
import com.tam.data.model.entity.User

interface UserDao : InsertDao<User>, GetByIdDao<User> {
    fun getUserByUsername(username: String): User?
}