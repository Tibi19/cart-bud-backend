package com.tam.data.table

import com.tam.data.model.Member
import org.ktorm.schema.Table
import org.ktorm.schema.int

object Members : Table<Member>("t_members") {
    val userPk = int("user_pk").bindTo { it.userPk }
    val groupPk = int("group_pk").bindTo { it.groupPk }
}