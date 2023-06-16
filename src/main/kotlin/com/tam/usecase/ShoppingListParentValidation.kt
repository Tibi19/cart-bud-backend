package com.tam.usecase

import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.repository.contract.GroupRepository
import com.tam.data.repository.contract.UserRepository

fun ShoppingListRequest.validateParent(
    userId: String,
    groupRepository: GroupRepository,
    userRepository: UserRepository
): Boolean {
    if (hasGroupParent) {
        val groupParent = groupRepository.getGroupById(parentId) ?: return false
        groupRepository
            .getGroupsByUserId(userId)
            ?.any { it.id == groupParent.id }
            ?: return false
        return true
    }

    userRepository.getUserByUserId(parentId) ?: return false
    return true
}