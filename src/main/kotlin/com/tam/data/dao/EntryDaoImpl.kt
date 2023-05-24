package com.tam.data.dao

import com.tam.data.dao.contract.entity.EntryDao
import com.tam.data.model.entity.Entry
import org.ktorm.database.Database

class EntryDaoImpl(private val database: Database) : EntryDao {
    override fun changeCheck(): Boolean {
        TODO("Not yet implemented")
    }

    override fun insert(element: Entry): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(element: Entry): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(element: Entry): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllByParent(parentId: String): List<Entry>? {
        TODO("Not yet implemented")
    }
}