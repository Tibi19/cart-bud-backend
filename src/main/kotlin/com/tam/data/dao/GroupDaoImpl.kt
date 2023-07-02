package com.tam.data.dao

import com.tam.data.dao.contract.entity.GroupDao
import com.tam.data.model.entity.Group
import com.tam.data.table.groups
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

class GroupDaoImpl(database: Database) : GroupDao {

    private val groups = database.groups

    override fun insert(element: Group): Boolean =
        groups.tryAdd(element)

    override fun update(element: Group): Boolean =
        groups.tryUpdate(element)

    override fun delete(elementId: String): Boolean =
        groups.tryRemoveIf { it.id eq elementId }

    override fun getById(id: String): Group? =
        tryGet {
            groups.find { it.id eq id }
        }

    override fun getByIds(ids: List<String>): List<Group>? =
        tryGet {
            groups
                .filter { it.id inList ids }
                .toList()
        }

}