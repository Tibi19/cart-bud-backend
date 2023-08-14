package com.tam.usecase

import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.repository.contract.GroupRepository

fun ShoppingListRequest.validateGroupParent(
    userId: String,
    groupRepository: GroupRepository
): Boolean {
    if (!hasGroupParent) return false

    val groupParent = groupRepository.getGroupById(parentId) ?: return false
    groupRepository
        .getGroupsByUserId(userId)
        ?.any { it.id == groupParent.id }
        ?: return false
    return true
}