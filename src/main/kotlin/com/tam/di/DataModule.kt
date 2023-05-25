package com.tam.di

import org.koin.dsl.module
import org.ktorm.database.Database

val dataModule = module {
    single<Database> { provideDatabase() }
    // TODO add repository provision
}

const val DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/mock_db" // TODO change to cartbud db pass
const val DB_USER = "root" // TODO move to environment variables
const val ENVIRONMENT_DB_PASSWORD = "MOCK_DB_PW" // TODO add cartbud db pass

private fun provideDatabase(): Database =
    Database.connect(
        url = DB_CONNECTION_URL,
        user = DB_USER,
        password = ENVIRONMENT_DB_PASSWORD
    )