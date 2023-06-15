package com.tam.data.table

import com.tam.data.model.entity.User
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

open class Users(alias: String?) : Table<User>("t_users", alias) {
    val pk = int("pk").primaryKey().bindTo { it.pk }
    val id = varchar("id").bindTo { it.id }
    val username = varchar("username").bindTo { it.username }
    val password = varchar("password").bindTo { it.password }
    val salt = varchar("salt").bindTo { it.salt }

    override fun aliased(alias: String): Table<User> = Users(alias)
    companion object : Users(null)
}