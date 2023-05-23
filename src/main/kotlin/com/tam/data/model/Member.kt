package com.tam.data.model

import org.ktorm.entity.Entity

interface Member : Entity<Member> {
    companion object : Entity.Factory<Member>()
    val userPk: Int
    val groupPk: Int
}