package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListRequest(
    val id: String,
    val parentId: String,
    val name: String,
    val hasGroupParent: Boolean,
    val timestampOfLastChange: Long
)
