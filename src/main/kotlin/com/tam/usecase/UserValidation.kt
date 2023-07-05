package com.tam.usecase

import com.tam.data.model.request.AuthRequest

private const val MINIMUM_PASS_LENGTH = 8
private const val REGEX_CONTAINS_UPPERCASE = "(.*[A-Z].*)"
private const val REGEX_CONTAINS_DIGITS = "(.*\\d.*)"

fun AuthRequest.validate(): Boolean =
    validateUsername() && validatePassword()

private fun AuthRequest.validateUsername() =
    username.isNotBlank()

private fun AuthRequest.validatePassword() =
    password.isNotBlank()
            && password.length >= MINIMUM_PASS_LENGTH
            && password.contains(Regex(REGEX_CONTAINS_DIGITS))
            && password.contains(Regex(REGEX_CONTAINS_UPPERCASE))