package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class EntryRequest(
    val id: String,
    val parentListId: String,
    val text: String,
    val isCompleted: Boolean,
    val timestampOfLastChange: Long
)
