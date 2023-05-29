package com.tam.data.mapper

import com.tam.data.model.entity.ShoppingList
import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.model.response.ShoppingListResponse

fun ShoppingListRequest.toShoppingList(): ShoppingList =
    ShoppingList.create(
        id = id,
        parentId = parentId,
        name = name,
        hasGroupParent = hasGroupParent,
        timestampOfLastChange = timestampOfLastChange
    )

fun ShoppingList.toShoppingListResponse(): ShoppingListResponse =
    ShoppingListResponse(
        id = id,
        parentId = parentId,
        name = name,
        hasGroupParent = hasGroupParent,
        timestampOfLastChange = timestampOfLastChange
    )