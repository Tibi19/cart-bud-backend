package com.tam.usecase

import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.repository.Repository

fun ShoppingListRequest.validateParent(repository: Repository): Boolean {
    if (hasGroupParent) {
        repository.getGroupByGroupId(parentId) ?: return false
    }

    repository.getUserByUserId(parentId) ?: return false

    return true
}