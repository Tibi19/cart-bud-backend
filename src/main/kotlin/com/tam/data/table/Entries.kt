package com.tam.data.table

import com.tam.data.model.entity.Entry
import org.ktorm.schema.*

open class Entries(alias: String?) : Table<Entry>("t_entries", alias) {
    val pk = int("pk").primaryKey().bindTo { it.pk }
    val id = varchar("id").bindTo { it.id }
    val parentPk = int("parent_pk").references(ShoppingLists) { it.parent }
    val text = varchar("text").bindTo { it.text }
    val isChecked = boolean("is_checked").bindTo { it.isChecked }
    val timestampOfLastChange = long("change_timestamp").bindTo { it.timestampOfLastChange }

    val parent get() = parentPk.referenceTable as ShoppingLists

    override fun aliased(alias: String): Table<Entry> = Entries(alias)
    companion object : Entries(null)
}