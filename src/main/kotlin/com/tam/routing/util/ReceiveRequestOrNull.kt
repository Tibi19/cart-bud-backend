package com.tam.routing.util

import io.ktor.server.application.*
import io.ktor.server.request.*

suspend inline fun <reified T> ApplicationCall.receiveRequestOrNull(): T? =
    runCatching {
        receiveNullable<T>()
    }
        .getOrNull()