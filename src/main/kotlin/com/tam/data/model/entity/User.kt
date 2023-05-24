package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface User : Entity<User> {
    val pk: Int
    var id: String
    var username: String
    var password: String
    var salt: String

    companion object : Entity.Factory<User>() {
        fun create(
            id: String,
            username: String,
            password: String,
            salt: String
        ): User =
            User {
                this.id = id
                this.username = username
                this.password = password
                this.salt = salt
            }
    }

}