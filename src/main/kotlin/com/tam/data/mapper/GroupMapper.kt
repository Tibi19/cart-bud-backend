package com.tam.data.mapper

import com.tam.data.model.entity.Group
import com.tam.data.model.entity.User
import com.tam.data.model.request.GroupRequest
import com.tam.data.model.response.GroupResponse

fun Group.toGroupResponse(memberId: String): GroupResponse =
    GroupResponse(
        id = id,
        name = name,
        adminName = admin.username,
        isAdmin = admin.id == memberId
    )

fun GroupRequest.toGroup(admin: User, pk: Int = 0): Group =
    Group.create(
        pk = pk,
        id = id,
        admin = admin,
        name = name
    )