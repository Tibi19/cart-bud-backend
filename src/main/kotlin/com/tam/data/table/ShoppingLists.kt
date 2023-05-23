package com.tam.data.table

import com.tam.data.model.ShoppingList
import org.ktorm.schema.*

object ShoppingLists : Table<ShoppingList>("t_shopping_lists") {
    val pk = int("pk").primaryKey().bindTo { it.pk }
    val id = varchar("id").bindTo { it.id }
    val parentId = varchar("parent_id").bindTo { it.parentId }
    val name = varchar("name").bindTo { it.name }
    val hasGroupParent = boolean("has_group_parent").bindTo { it.hasGroupParent }
    val timestampOfLastChange = long("change_timestamp").bindTo { it.timestampOfLastChange }
}