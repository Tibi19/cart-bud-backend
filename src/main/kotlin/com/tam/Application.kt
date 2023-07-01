package com.tam

import com.tam.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureDependencyInjection(environment)
    configureMonitoring()
    configureSerialization()
    configureCors()
    configureSecurity()
    configureRouting()
}
