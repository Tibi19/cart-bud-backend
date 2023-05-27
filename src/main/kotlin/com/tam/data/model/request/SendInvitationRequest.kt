package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendInvitationRequest(
    val toUsername: String,
    val onGroupId: String
)
