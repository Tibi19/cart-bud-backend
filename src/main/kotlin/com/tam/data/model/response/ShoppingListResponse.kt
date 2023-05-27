package com.tam.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListResponse(
    val id: String,
    val parentId: String,
    val name: String,
    val hasGroupParent: String,
    val timestampOfLastChange: Long
)
