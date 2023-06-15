package com.tam.data.mapper

import com.tam.data.model.entity.Invitation
import com.tam.data.model.entity.User
import com.tam.data.model.response.InvitationResponse

fun Invitation.toInvitationResponse(admin: User): InvitationResponse =
    InvitationResponse(
        fromAdminName = admin.username,
        groupId = group.id,
        groupName = group.name
    )