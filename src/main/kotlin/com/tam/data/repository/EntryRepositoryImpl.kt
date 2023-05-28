package com.tam.data.repository

import com.tam.data.model.request.EntryRequest
import com.tam.data.model.response.EntryResponse
import com.tam.data.repository.contract.EntryRepository

class EntryRepositoryImpl : EntryRepository {
    override fun createEntry(entryRequest: EntryRequest): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEntriesByParentId(parentListId: String): List<EntryResponse>? {
        TODO("Not yet implemented")
    }

    override fun updateEntry(entryRequest: EntryRequest): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteEntry(entryRequest: EntryRequest): Boolean {
        TODO("Not yet implemented")
    }
}