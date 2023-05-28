package com.tam.data.repository.contract

import com.tam.data.model.request.EntryRequest
import com.tam.data.model.response.EntryResponse

interface EntryRepository {
    fun createEntry(entryRequest: EntryRequest): Boolean
    fun getEntriesByParentId(parentListId: String): List<EntryResponse>?
    fun updateEntry(entryRequest: EntryRequest): Boolean
    fun deleteEntry(entryRequest: EntryRequest): Boolean
}