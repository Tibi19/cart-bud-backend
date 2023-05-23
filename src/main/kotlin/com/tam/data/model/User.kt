package com.tam.data.model

import org.ktorm.entity.Entity

interface User : Entity<User> {
    companion object : Entity.Factory<User>()
    val pk: Int
    val id: String
    var username: String
    var password: String
    var salt: String
}