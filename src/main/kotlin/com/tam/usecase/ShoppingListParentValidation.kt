package com.tam.usecase

import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.repository.contract.GroupRepository
import com.tam.data.repository.contract.UserRepository

fun ShoppingListRequest.validateParent(
    groupRepository: GroupRepository,
    userRepository: UserRepository
): Boolean {
    if (hasGroupParent) {
        groupRepository.getGroupById(parentId) ?: return false
    }

    userRepository.getUserByUserId(parentId) ?: return false

    return true
}