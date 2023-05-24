package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface Member : Entity<Member> {
    var user: User
    var group: Group

    companion object : Entity.Factory<Member>() {
        fun create(user: User, group: Group): Member =
            Member {
                this.user = user
                this.group = group
            }
    }

}