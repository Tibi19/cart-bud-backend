package com.tam.data.dao

import com.tam.data.dao.contract.entity.ShoppingListDao
import com.tam.data.model.entity.ShoppingList
import org.ktorm.database.Database

class ShoppingListDaoImpl(private val database: Database) : ShoppingListDao {
    override fun update(element: ShoppingList): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(element: ShoppingList): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllByParent(parentId: String): List<ShoppingList>? {
        TODO("Not yet implemented")
    }

    override fun insert(element: ShoppingList): Boolean {
        TODO("Not yet implemented")
    }
}