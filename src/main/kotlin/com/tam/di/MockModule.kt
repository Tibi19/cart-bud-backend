package com.tam.di

import com.tam.mockdata.TestRepository
import com.tam.mockdata.TestRepoImpl
import com.tam.mockdata.MockService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.module.dsl.bind

val appModule = module {
    singleOf(::TestRepoImpl) { bind<TestRepository>() }
    singleOf(::MockService)
}