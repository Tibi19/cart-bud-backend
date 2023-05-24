package com.tam.data.table

import com.tam.data.model.entity.Entry
import org.ktorm.schema.*

object Entries : Table<Entry>("t_entries") {
    val pk = int("pk").primaryKey().bindTo { it.pk }
    val id = varchar("id").bindTo { it.id }
    val parentPk = varchar("parent_pk").references(ShoppingLists) { it.parent }
    val text = varchar("text").bindTo { it.text }
    val isChecked = boolean("is_checked").bindTo { it.isChecked }
    val timestampOfLastChange = long("change_timestamp").bindTo { it.timestampOfLastChange }

    val parent get() = parentPk.referenceTable as ShoppingLists
}