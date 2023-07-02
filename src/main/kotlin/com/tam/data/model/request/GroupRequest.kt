package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class GroupRequest(
    val id: String,
    val name: String,
    val timestampOfLastChange: Long
)