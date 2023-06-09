package com.tam.data.dao

import com.tam.data.dao.contract.entity.ShoppingListDao
import com.tam.data.model.entity.ShoppingList
import com.tam.data.table.shoppingLists
import com.tam.data.util.tryAdd
import com.tam.data.util.tryGet
import com.tam.data.util.tryRemoveIf
import com.tam.data.util.tryUpdate
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.inList
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.toList

class ShoppingListDaoImpl(database: Database) : ShoppingListDao {

    private val shoppingLists = database.shoppingLists

    override fun insert(element: ShoppingList): Boolean =
        shoppingLists.tryAdd(element)

    override fun update(element: ShoppingList): Boolean =
        shoppingLists.tryUpdate(element)

    override fun delete(elementId: String): Boolean =
        shoppingLists.tryRemoveIf { it.id eq elementId }

    override fun getById(id: String): ShoppingList? =
        tryGet {
            shoppingLists.find { it.id eq id }
        }

    override fun getByIds(ids: List<String>): List<ShoppingList>? =
        tryGet {
            shoppingLists
                .filter { it.id inList ids }
                .toList()
        }

    override fun getAllByParentId(parentId: String): List<ShoppingList>? =
        tryGet {
            shoppingLists
                .filter { it.parentId eq parentId }
                .toList()
        }

}