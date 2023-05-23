package com.tam

import com.tam.mockdata.MockService
import com.tam.mockdata.MockTable
import com.tam.plugins.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureDependencyInjection()

    val service by inject<MockService>()
    val mockData = service.getMockData()
    routing {
        get("/mock") {
            call.respondText("${mockData.name} with description: ${mockData.description}")
        }
    }

    val dbPassword = System.getenv("MOCK_DB_PW")
    val database = Database.connect("jdbc:mysql://localhost:3306/mock_db", user = "root", password=dbPassword)
    val elements = database.sequenceOf(MockTable).toList()
    routing {
        get("/mock_db") {
            val text = elements.joinToString("\n") { "${it.name} with description ${it.description}" }
            call.respondText(text)
        }
    }

    configureMonitoring()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
