package com.tam.data.table

import com.tam.data.model.entity.Invitation
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

open class Invitations(alias: String?) : Table<Invitation>("t_invitations", alias) {
    val userPk = int("user_pk").primaryKey().references(Users) { it.user }
    val groupPk = int("group_pk").primaryKey().references(Groups) { it.group }
    val adminId = varchar("admin_id").bindTo { it.adminId }

    val user get() = userPk.referenceTable as Users
    val group get() = groupPk.referenceTable as Groups

    override fun aliased(alias: String): Table<Invitation> = Invitations(alias)
    companion object : Invitations(null)
}