package com.tam.routing.util

import com.tam.routing.KEY_USER_ID
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun ApplicationCall.receiveUserIdOrNull(): String? =
    principal<JWTPrincipal>()?.getClaim(KEY_USER_ID, String::class)