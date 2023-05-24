package com.tam.data.dao

import com.tam.data.dao.contract.entity.GroupDao
import com.tam.data.model.entity.Group
import org.ktorm.database.Database

class GroupDaoImpl(private val database: Database) : GroupDao {
    override fun insert(element: Group): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(element: Group): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(element: Group): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllByParent(parentId: String): List<Group>? {
        TODO("Not yet implemented")
    }
}