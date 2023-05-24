package com.tam.data.table

import com.tam.data.model.entity.Member
import org.ktorm.schema.Table
import org.ktorm.schema.int

object Members : Table<Member>("t_members") {
    val userPk = int("user_pk").references(Users) { it.user }
    val groupPk = int("group_pk").references(Groups) { it.group }

    val user get() = userPk.referenceTable as Users
    val group get() = groupPk.referenceTable as Groups
}