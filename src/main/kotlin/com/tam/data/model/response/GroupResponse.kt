package com.tam.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GroupResponse(
    val id: String,
    val name: String,
    val adminName: String,
    val isAdmin: Boolean,
    val timestampOfLastChange: Long
)
