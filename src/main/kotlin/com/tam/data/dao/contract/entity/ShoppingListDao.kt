package com.tam.data.dao.contract.entity

import com.tam.data.dao.contract.operation.*
import com.tam.data.model.entity.ShoppingList

interface ShoppingListDao :
    InsertDao<ShoppingList>,
    EditDao<ShoppingList>,
    GetAllByParentDao<ShoppingList>,
    GetByIdDao<ShoppingList>,
    GetByIdsDao<ShoppingList>