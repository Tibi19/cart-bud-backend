package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface Invitation : Entity<Invitation> {
    var user: User
    var group: Group
    var adminId: String

    companion object : Entity.Factory<Invitation>() {
        fun create(
            user: User,
            group: Group,
            adminId: String
        ): Invitation =
            Invitation {
                this.user = user
                this.group = group
                this.adminId = adminId
            }
    }

}