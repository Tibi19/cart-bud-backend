package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface Group : Entity<Group> {
    val pk: Int
    var id: String
    var admin: User
    var name: String

    companion object : Entity.Factory<Group>() {
        fun create(
            id: String,
            admin: User,
            name: String
        ): Group =
            Group {
                this.id = id
                this.admin = admin
                this.name = name
            }
    }

}