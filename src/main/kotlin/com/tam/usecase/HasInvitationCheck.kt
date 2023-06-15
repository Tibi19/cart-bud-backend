package com.tam.usecase

import com.tam.data.repository.contract.InvitationRepository

fun hasInvitation(
    repository: InvitationRepository,
    groupId: String,
    userId: String,
) : Boolean =
    repository
        .getUserInvitations(userId)
        ?.any { invitation ->
            invitation.groupId == groupId
        } ?: false