package com.tam.di

import com.tam.data.dao.*
import com.tam.data.dao.contract.entity.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val daoModule = module {
    singleOf(::UserDaoImpl) bind UserDao::class
    singleOf(::GroupDaoImpl) bind GroupDao::class
    singleOf(::MemberDaoImpl) bind MemberDao::class
    singleOf(::InvitationDaoImpl) bind InvitationDao::class
    singleOf(::ShoppingListDaoImpl) bind ShoppingListDao::class
    singleOf(::EntryDaoImpl) bind EntryDao::class
}