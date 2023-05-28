package com.tam.usecase

import com.tam.data.repository.contract.GroupRepository

fun isGroupAdmin(
    repository: GroupRepository,
    groupId: String,
    userId: String
): Boolean =
    repository
        .getGroupById(groupId)
        ?.admin
        ?.id == userId