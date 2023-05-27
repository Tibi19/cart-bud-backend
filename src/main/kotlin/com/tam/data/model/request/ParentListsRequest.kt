package com.tam.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ParentListsRequest(
    val parentId: String
)
