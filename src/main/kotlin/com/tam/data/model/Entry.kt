package com.tam.data.model

import org.ktorm.entity.Entity

interface Entry : Entity<Entry> {
    companion object : Entity.Factory<Entry>()
    val pk: Int
    val id: String
    var parentId: String
    var text: String
    var isChecked: Boolean
    var timestampOfLastChange: Long
}