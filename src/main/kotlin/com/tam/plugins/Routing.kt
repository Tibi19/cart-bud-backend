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

        changes()
    }
}

private fun Routing.authenticationRoutes() {
    signIn()
    signUp()
    authenticate()
}

private fun Routing.groupRoutes() {
    createGroup()
    userGroups()
    updateGroup()
    deleteGroup()
}

private fun Routing.invitationRoutes() {
    sendInvitation()
    getUserInvitations()
    acceptInvitation()
}

private fun Routing.shoppingListRoutes() {
    createShoppingList()
    userShoppingLists()
    groupShoppingLists()
    updateShoppingList()
    deleteShoppingList()
}

private fun Routing.entryRoutes() {
    createEntry()
    shoppingListEntries()
    updateEntry()
    deleteEntry()
}
