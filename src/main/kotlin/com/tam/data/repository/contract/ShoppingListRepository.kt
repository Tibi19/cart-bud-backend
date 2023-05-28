package com.tam.data.repository.contract

import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.model.response.ShoppingListResponse

interface ShoppingListRepository {
    fun createShoppingList(shoppingListRequest: ShoppingListRequest): Boolean
    fun getShoppingListsByParentId(parentId: String): List<ShoppingListResponse>?
    fun updateShoppingList(shoppingListRequest: ShoppingListRequest): Boolean
    fun deleteShoppingList(shoppingListRequest: ShoppingListRequest): Boolean
}