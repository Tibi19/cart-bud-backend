package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AcceptInvitationRequest(
    val onGroupId: String
)
