package com.tam.data.repository

import com.tam.data.dao.contract.entity.EntryDao
import com.tam.data.dao.contract.entity.ShoppingListDao
import com.tam.data.mapper.toEntry
import com.tam.data.mapper.toEntryResponse
import com.tam.data.mapper.toEntryResponses
import com.tam.data.model.request.EntryRequest
import com.tam.data.model.response.EntryResponse
import com.tam.data.repository.contract.EntryRepository

class EntryRepositoryImpl(
    private val entryDao: EntryDao,
    private val shoppingListDao: ShoppingListDao
) : EntryRepository {

    override fun createEntry(entryRequest: EntryRequest): Boolean {
        val shoppingListParent = shoppingListDao.getById(entryRequest.parentListId) ?: return false
        return entryDao.insert(entryRequest.toEntry(shoppingListParent))
    }

    override fun getEntriesByParentId(parentListId: String): List<EntryResponse>? =
        entryDao
            .getAllByParentId(parentListId)
            ?.map { entry ->
                entry.toEntryResponse()
            }

    override fun updateEntry(entryRequest: EntryRequest): Boolean {
        val shoppingListParent = shoppingListDao.getById(entryRequest.parentListId) ?: return false
        val entry = entryDao.getById(entryRequest.id) ?: return false
        val changedEntry = entryRequest.toEntry(shoppingListParent, entry.pk)
        return entryDao.update(changedEntry)
    }

    override fun deleteEntry(entryRequest: EntryRequest): Boolean =
        entryDao.delete(entryRequest.id)

    override fun getEntriesByIds(ids: List<String>): List<EntryResponse>? =
        entryDao
            .getByIds(ids)
            ?.toEntryResponses()

}