package com.tam.routing.util

import io.ktor.http.*
import io.ktor.server.application.*

suspend inline fun <reified RequestType> ApplicationCall.receiveRequestInfoWithErrorHandle(
    crossinline onHttpError: suspend (statusCode: HttpStatusCode) -> Unit
): RequestInfo<RequestType>? {
    val userId = receiveUserIdOrNull()
        ?: run {
            onHttpError(HttpStatusCode.Unauthorized)
            return null
        }

    val request = receiveRequestOrNull<RequestType>()
        ?: run {
            onHttpError(HttpStatusCode.BadRequest)
            return null
        }

    return RequestInfo(userId, request)
}

data class RequestInfo<T>(
    val userId: String,
    val request: T
)