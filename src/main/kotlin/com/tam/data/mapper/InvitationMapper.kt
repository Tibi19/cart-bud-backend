package com.tam.data.mapper

import com.tam.data.model.entity.Invitation
import com.tam.data.model.response.InvitationResponse

fun Invitation.toInvitationResponse(): InvitationResponse =
    InvitationResponse(
        fromAdminName = admin.username,
        groupId = group.id,
        groupName = group.name
    )