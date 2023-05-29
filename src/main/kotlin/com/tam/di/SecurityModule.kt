package com.tam.di

import com.tam.security.hashing.HashingService
import com.tam.security.hashing.SHA256HashingService
import com.tam.security.token.JwtTokenService
import com.tam.security.token.TokenConfig
import com.tam.security.token.TokenService
import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun securityModule(environment: ApplicationEnvironment): Module =
    module {
        singleOf(::JwtTokenService) bind TokenService::class
        singleOf(::SHA256HashingService) bind HashingService::class
        single<TokenConfig> { provideTokenConfig(environment) }
    }

const val DEFAULT_EXPIRATION_TIME = 365L * 1000L * 60L * 60L * 24L
const val PROPERTY_JWT_ISSUER = "jwt.issuer"
const val PROPERTY_JWT_AUDIENCE = "jwt.audience"
const val ENVIRONMENT_JWT_SECRET = "JWT_SECRET"

private fun provideTokenConfig(environment: ApplicationEnvironment): TokenConfig =
    TokenConfig(
        issuer = environment.config.property(PROPERTY_JWT_ISSUER).getString(),
        audience = environment.config.property(PROPERTY_JWT_AUDIENCE).getString(),
        expiresIn = DEFAULT_EXPIRATION_TIME,
        secret = System.getenv(ENVIRONMENT_JWT_SECRET)
    )