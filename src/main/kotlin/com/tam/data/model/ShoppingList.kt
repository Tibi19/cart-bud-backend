package com.tam.data.model

import org.ktorm.entity.Entity

interface ShoppingList : Entity<ShoppingList> {
    companion object : Entity.Factory<ShoppingList>()
    val pk: Int
    val id: String
    val parentId: String
    var name: String
    var hasGroupParent: Boolean
    var timestampOfLastChange: Long
}