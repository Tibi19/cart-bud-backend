package com.tam.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ChangesResponse(
    val changesExist: Boolean,
    val groups: List<GroupResponse>,
    val shoppingLists: List<ShoppingListResponse>,
    val entries: List<EntryResponse>,
    val deletedIds: List<String>
)
