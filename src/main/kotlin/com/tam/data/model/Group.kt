package com.tam.data.model

import org.ktorm.entity.Entity

interface Group : Entity<Group> {
    companion object : Entity.Factory<Group>()
    val pk: Int
    val id: String
    var adminId: String
    var name: String
}