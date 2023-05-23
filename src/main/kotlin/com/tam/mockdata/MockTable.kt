package com.tam.mockdata

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object MockTable : Table<MockEntity>("table_mock") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val description = varchar("description").bindTo { it.description }
}