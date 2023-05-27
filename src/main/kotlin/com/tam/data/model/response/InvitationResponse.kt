package com.tam.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class InvitationResponse(
    val fromAdminName: String,
    val groupId: String,
    val groupName: String
)
