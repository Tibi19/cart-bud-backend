package com.tam.plugins

import com.tam.routing.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        authenticationRoutes()
        groupRoutes()
        invitationRoutes()
        shoppingListRoutes()
        entryRoutes()
    }
}

private fun Routing.authenticationRoutes() {
    signIn()
    signUp()
    authenticate()
}

private fun Routing.groupRoutes() {
    createGroup()
    getGroupsByUserId()
    updateGroup()
    deleteGroup()
}

private fun Routing.invitationRoutes() {

}

private fun Routing.shoppingListRoutes() {

}

private fun Routing.entryRoutes() {

}
