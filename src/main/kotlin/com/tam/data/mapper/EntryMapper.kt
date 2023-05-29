package com.tam.data.mapper

import com.tam.data.model.entity.Entry
import com.tam.data.model.entity.ShoppingList
import com.tam.data.model.request.EntryRequest
import com.tam.data.model.response.EntryResponse

fun EntryRequest.toEntry(parent: ShoppingList): Entry =
    Entry.create(
        id = id,
        parent = parent,
        text = text,
        isChecked = isCompleted,
        timestampOfLastChange = timestampOfLastChange
    )

fun Entry.toEntryResponse(): EntryResponse =
    EntryResponse(
        id = id,
        parentListId = parent.id,
        text = text,
        isCompleted = isChecked,
        timestampOfLastChange = timestampOfLastChange
    )