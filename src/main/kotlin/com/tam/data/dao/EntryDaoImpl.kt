package com.tam.data.dao

import com.tam.data.dao.contract.entity.EntryDao
import com.tam.data.model.entity.Entry
import com.tam.data.table.entries
import com.tam.data.util.tryAdd
import com.tam.data.util.tryGet
import com.tam.data.util.tryRemoveIf
import com.tam.data.util.tryUpdate
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.toList
import org.ktorm.entity.find

class EntryDaoImpl(database: Database) : EntryDao {

    private val entries = database.entries

    override fun insert(element: Entry): Boolean =
        entries.tryAdd(element)

    override fun update(element: Entry): Boolean =
        entries.tryUpdate(element)

    override fun delete(elementId: String): Boolean =
        entries.tryRemoveIf { it.id eq elementId }

    override fun getAllByParentId(parentId: String): List<Entry>? =
        tryGet {
            entries
                .filter { it.parent.id eq parentId }
                .toList()
        }

    override fun getById(id: String): Entry? =
        tryGet {
            entries.find { it.id eq id }
        }

}