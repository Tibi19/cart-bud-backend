package com.tam.data.table

import com.tam.data.model.entity.Invitation
import org.ktorm.schema.Table
import org.ktorm.schema.int

open class Invitations(alias: String?) : Table<Invitation>("t_invitations", alias) {
    val userPk = int("user_pk").primaryKey().references(Users) { it.user }
    val groupPk = int("group_pk").primaryKey().references(Groups) { it.group }
    val adminPk = int("admin_pk").references(Users) { it.user }

    val user get() = userPk.referenceTable as Users
    val group get() = groupPk.referenceTable as Groups
    val admin get() = adminPk.referenceTable as Users

    override fun aliased(alias: String): Table<Invitation> = Invitations(alias)
    companion object : Invitations(null)
}