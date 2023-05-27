package com.tam.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class EntryResponse(
    val id: String,
    val parentListId: String,
    val text: String,
    val isCompleted: Boolean,
    val timestampOfLastChange: Long
)
