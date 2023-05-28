package com.tam.data.repository

import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.model.response.ShoppingListResponse
import com.tam.data.repository.contract.ShoppingListRepository

class ShoppingListRepositoryImpl : ShoppingListRepository {
    override fun createShoppingList(shoppingListRequest: ShoppingListRequest): Boolean {
        TODO("Not yet implemented")
    }

    override fun getShoppingListsByParentId(parentId: String): List<ShoppingListResponse>? {
        TODO("Not yet implemented")
    }

    override fun updateShoppingList(shoppingListRequest: ShoppingListRequest): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteShoppingList(shoppingListRequest: ShoppingListRequest): Boolean {
        TODO("Not yet implemented")
    }
}