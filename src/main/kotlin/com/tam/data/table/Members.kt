package com.tam.data.table

import com.tam.data.model.entity.Member
import org.ktorm.schema.Table
import org.ktorm.schema.int

open class Members(alias: String?) : Table<Member>("t_members", alias) {
    val userPk = int("user_pk").primaryKey().references(Users) { it.user }
    val groupPk = int("group_pk").primaryKey().references(Groups) { it.group }

    val user get() = userPk.referenceTable as Users
    val group get() = groupPk.referenceTable as Groups

    override fun aliased(alias: String): Table<Member> = Members(alias)
    companion object : Members(null)
}