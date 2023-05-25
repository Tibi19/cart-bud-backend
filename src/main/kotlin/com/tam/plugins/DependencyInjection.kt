package com.tam.plugins

import com.tam.di.dataModule
import com.tam.di.securityModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDependencyInjection(environment: ApplicationEnvironment) {
    install(Koin) {
        slf4jLogger()
        modules(
            dataModule,
            securityModule(environment)
        )
    }
}