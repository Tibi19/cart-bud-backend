package com.tam.data.model.entity

import org.ktorm.entity.Entity

interface Entry : Entity<Entry> {
    var pk: Int
    var id: String
    var parent: ShoppingList
    var text: String
    var isChecked: Boolean
    var timestampOfLastChange: Long

    companion object : Entity.Factory<Entry>() {
        fun create(
            id: String,
            parent: ShoppingList,
            text: String,
            isChecked: Boolean,
            timestampOfLastChange: Long,
            pk: Int = 0
        ): Entry =
            Entry {
                this.pk = pk
                this.id = id
                this.parent = parent
                this.text = text
                this.isChecked = isChecked
                this.timestampOfLastChange = timestampOfLastChange
            }
    }

}