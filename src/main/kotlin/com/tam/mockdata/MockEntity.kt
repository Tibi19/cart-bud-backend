package com.tam.mockdata

import org.ktorm.entity.Entity

interface MockEntity : Entity<MockEntity> {
    companion object : Entity.Factory<MockEntity>()
    val id: Int
    var name: String
    var description: String
}