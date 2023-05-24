package com.tam.data.table

import com.tam.data.model.entity.Group
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Groups : Table<Group>("t_groups") {
    val pk = int("pk").primaryKey().bindTo { it.pk }
    val id = varchar("id").bindTo { it.id }
    val adminPk = int("admin_pk").references(Users) { it.admin }
    val name = varchar("name").bindTo { it.name }

    val admin get() = adminPk.referenceTable as Users
}