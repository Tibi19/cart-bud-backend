package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class GroupRequest(
    var id: String,
    var name: String
)