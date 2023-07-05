package com.tam.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.tam.security.token.TokenConfig
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

private const val JWT_REALM = "jwt.realm"

fun Application.configureSecurity() {
    val config by inject<TokenConfig>()

    authentication {
        jwt {
            realm = this@configureSecurity.environment.config.property(JWT_REALM).getString()
            verifier(
                JWT
                    .require(Algorithm.HMAC256(config.secret))
                    .withAudience(config.audience)
                    .withIssuer(config.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(config.audience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }

}
