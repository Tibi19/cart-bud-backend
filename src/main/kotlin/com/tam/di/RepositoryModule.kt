package com.tam.di

import com.tam.data.repository.*
import com.tam.data.repository.contract.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::GroupRepositoryImpl) bind GroupRepository::class
    singleOf(::InvitationRepositoryImpl) bind InvitationRepository::class
    singleOf(::ShoppingListRepositoryImpl) bind ShoppingListRepository::class
    singleOf(::EntryRepositoryImpl) bind EntryRepository::class
}