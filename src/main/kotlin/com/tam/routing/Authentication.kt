package com.tam.routing

import com.tam.data.model.request.AuthRequest
import com.tam.data.model.response.AuthResponse
import com.tam.data.repository.Repository
import com.tam.routing.util.receiveRequestOrNull
import com.tam.security.hashing.HashingService
import com.tam.security.hashing.SaltedHash
import com.tam.security.token.TokenClaim
import com.tam.security.token.TokenConfig
import com.tam.security.token.TokenService
import com.tam.usecase.validate
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ROUTE_SIGNUP = "signup"
const val ROUTE_SIGNIN = "signin"
const val ROUTE_AUTHENTICATE = "authenticate"

const val KEY_USER_ID = "userId"

const val ERROR_VALIDATION = "Fields validation error"
const val ERROR_USER_EXISTS = "User already exists"
const val ERROR_USER_OR_PASSWORD = "Incorrect username or password"

fun Route.signUp() {
    val hashingService by inject<HashingService>()
    val repository by inject<Repository>()

    post(ROUTE_SIGNUP) {
        val request = call.receiveRequestOrNull<AuthRequest>()
            ?: run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

        if (!request.validate()) {
            call.respond(HttpStatusCode.Conflict, ERROR_VALIDATION)
            return@post
        }

        repository.getUserByUsername(request.username)?.let {
            call.respond(HttpStatusCode.Conflict, ERROR_USER_EXISTS)
            return@post
        }

        val saltedHash = hashingService.generateSaltedHash(request.password)
        val isOk = repository.createUser(request.username, saltedHash)

        if (!isOk) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        call.respond(HttpStatusCode.OK)
    }
}

fun Route.signIn() {
    val hashingService by inject<HashingService>()
    val tokenService by inject<TokenService>()
    val tokenConfig by inject<TokenConfig>()
    val repository by inject<Repository>()

    post(ROUTE_SIGNIN) {
        val request = call.receiveRequestOrNull<AuthRequest>()
            ?: run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

        val user = repository.getUserByUsername(request.username)
            ?: run {
                call.respond(HttpStatusCode.Conflict, ERROR_USER_OR_PASSWORD)
                return@post
            }

        val isValidPassword = hashingService.verify(
            value = request.password,
            saltedHash = SaltedHash(hash = user.password, salt = user.salt)
        )
        if (!isValidPassword) {
            call.respond(HttpStatusCode.Conflict, ERROR_USER_OR_PASSWORD)
            return@post
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(name = KEY_USER_ID, value = user.id)
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = AuthResponse(token)
        )
    }
}

fun Route.authenticate() {
    authenticate {
        get(ROUTE_AUTHENTICATE) {
            call.respond(HttpStatusCode.OK)
        }
    }
}