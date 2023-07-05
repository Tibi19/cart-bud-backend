package com.tam.di

import org.koin.dsl.module
import org.ktorm.database.Database

val databaseModule = module {
    single<Database> { provideDatabase() }
}

private const val DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cart_bud_db"
private const val ENVIRONMENT_DB_USER = "CART_BUD_DB_USER"
private const val ENVIRONMENT_DB_PASSWORD = "CART_BUD_DB_PW"

private fun provideDatabase(): Database =
    Database.connect(
        url = DB_CONNECTION_URL,
        user = System.getenv(ENVIRONMENT_DB_USER),
        password = System.getenv(ENVIRONMENT_DB_PASSWORD)
    )