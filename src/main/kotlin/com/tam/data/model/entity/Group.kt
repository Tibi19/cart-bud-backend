package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface Group : Entity<Group> {
    var pk: Int
    var id: String
    var admin: User
    var name: String
    var timestampOfLastChange: Long

    companion object : Entity.Factory<Group>() {
        fun create(
            id: String,
            admin: User,
            name: String,
            timestampOfLastChange: Long,
            pk: Int = 0
        ): Group =
            Group {
                this.pk = pk
                this.id = id
                this.admin = admin
                this.name = name
                this.timestampOfLastChange = timestampOfLastChange
            }
    }

}