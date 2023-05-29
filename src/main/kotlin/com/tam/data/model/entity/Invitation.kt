package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface Invitation : Entity<Invitation> {
    var user: User
    var group: Group
    var admin: User

    companion object : Entity.Factory<Invitation>() {
        fun create(
            user: User,
            group: Group,
            admin: User
        ): Invitation =
            Invitation {
                this.user = user
                this.group = group
                this.admin = admin
            }
    }

}