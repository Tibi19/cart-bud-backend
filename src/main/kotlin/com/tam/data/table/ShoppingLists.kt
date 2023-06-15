package com.tam.data.table

import com.tam.data.model.entity.ShoppingList
import org.ktorm.schema.*

open class ShoppingLists(alias: String?) : Table<ShoppingList>("t_shopping_lists", alias) {
    val pk = int("pk").primaryKey().bindTo { it.pk }
    val id = varchar("id").bindTo { it.id }
    val parentId = varchar("parent_id").bindTo { it.parentId }
    val name = varchar("name").bindTo { it.name }
    val hasGroupParent = boolean("has_group_parent").bindTo { it.hasGroupParent }
    val timestampOfLastChange = long("change_timestamp").bindTo { it.timestampOfLastChange }

    override fun aliased(alias: String): Table<ShoppingList> = ShoppingLists(alias)
    companion object : ShoppingLists(null)
}