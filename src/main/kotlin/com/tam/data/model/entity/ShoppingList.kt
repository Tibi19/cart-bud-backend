package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface ShoppingList : Entity<ShoppingList> {
    var pk: Int
    var id: String
    var parentId: String
    var name: String
    var hasGroupParent: Boolean
    var timestampOfLastChange: Long

    companion object : Entity.Factory<ShoppingList>() {
        fun create(
            id: String,
            parentId: String,
            name: String,
            hasGroupParent: Boolean,
            timestampOfLastChange: Long,
            pk: Int = 0
        ): ShoppingList =
            ShoppingList {
                this.pk = pk
                this.id = id
                this.parentId = parentId
                this.name = name
                this.hasGroupParent = hasGroupParent
                this.timestampOfLastChange = timestampOfLastChange
            }
    }

}