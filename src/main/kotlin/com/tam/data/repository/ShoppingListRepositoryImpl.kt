package com.tam.data.repository

import com.tam.data.dao.contract.entity.ShoppingListDao
import com.tam.data.mapper.toShoppingList
import com.tam.data.mapper.toShoppingListResponse
import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.model.response.ShoppingListResponse
import com.tam.data.repository.contract.ShoppingListRepository

class ShoppingListRepositoryImpl(private val shoppingListDao: ShoppingListDao) : ShoppingListRepository {

    override fun createShoppingList(shoppingListRequest: ShoppingListRequest): Boolean =
        shoppingListDao.insert(shoppingListRequest.toShoppingList())

    override fun getShoppingListsByParentId(parentId: String): List<ShoppingListResponse>? =
        shoppingListDao
            .getAllByParentId(parentId)
            ?.map { shoppingList ->
                shoppingList.toShoppingListResponse()
            }

    override fun updateShoppingList(shoppingListRequest: ShoppingListRequest): Boolean =
        shoppingListDao.update(shoppingListRequest.toShoppingList())

    override fun deleteShoppingList(shoppingListRequest: ShoppingListRequest): Boolean =
        shoppingListDao.delete(shoppingListRequest.id)

}