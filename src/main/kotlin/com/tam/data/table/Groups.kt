package com.tam.data.table

import com.tam.data.model.entity.Group
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.ktorm.schema.varchar

open class Groups(alias: String?) : Table<Group>("t_groups", alias) {
    val pk = int("pk").primaryKey().bindTo { it.pk }
    val id = varchar("id").bindTo { it.id }
    val adminPk = int("admin_pk").references(Users) { it.admin }
    val name = varchar("name").bindTo { it.name }
    val timestampOfLastChange = long("change_timestamp").bindTo { it.timestampOfLastChange }

    val admin get() = adminPk.referenceTable as Users

    override fun aliased(alias: String): Table<Group> = Groups(alias)
    companion object : Groups(null)
}