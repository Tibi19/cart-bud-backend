package com.tam.data.table

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

val Database.users get() = this.sequenceOf(Users)
val Database.groups get() = this.sequenceOf(Groups)
val Database.members get() = this.sequenceOf(Members)
val Database.invitations get() = this.sequenceOf(Invitations)
val Database.shoppingLists get() = this.sequenceOf(ShoppingLists)
val Database.entries get() = this.sequenceOf(Entries)