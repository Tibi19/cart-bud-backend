package com.tam.data.repository

import com.tam.data.model.entity.Group
import com.tam.data.model.entity.User
import com.tam.data.model.request.EntryRequest
import com.tam.data.model.request.GroupRequest
import com.tam.data.model.request.SendInvitationRequest
import com.tam.data.model.request.ShoppingListRequest
import com.tam.data.model.response.EntryResponse
import com.tam.data.model.response.GroupResponse
import com.tam.data.model.response.InvitationResponse
import com.tam.data.model.response.ShoppingListResponse
import com.tam.security.hashing.SaltedHash

interface Repository {

    fun createUser(username: String, saltedHash: SaltedHash): Boolean
    fun getUserByUsername(username: String): User?
    fun createGroup(groupRequest: GroupRequest, userId: String): Boolean
    fun getGroupsByUserId(userId: String): List<GroupResponse>?
    fun isGroupAdmin(groupId: String, userId: String): Boolean
    fun updateGroup(groupRequest: GroupRequest): Boolean
    fun deleteGroup(groupRequest: GroupRequest): Boolean
    fun createInvitation(fromAdminId: String, sendInvitationRequest: SendInvitationRequest): Boolean
    fun getUserInvitations(userId: String): List<InvitationResponse>?
    fun acceptInvitation(toUserId: String, onGroupId: String): Boolean
    fun deleteInvitation(toUserId: String, onGroupId: String): Boolean
    fun deleteGroupMember(userId: String, groupId: String): Boolean
    fun getGroupByGroupId(groupId: String): Group?
    fun getUserByUserId(userId: String): User?
    fun createShoppingList(shoppingListRequest: ShoppingListRequest): Boolean
    fun getShoppingListsByParentId(parentId: String): List<ShoppingListResponse>?
    fun updateShoppingList(shoppingListRequest: ShoppingListRequest): Boolean
    fun deleteShoppingList(shoppingListRequest: ShoppingListRequest): Boolean
    fun createEntry(entryRequest: EntryRequest): Boolean
    fun getEntriesByParentId(parentListId: String): List<EntryResponse>?
    fun updateEntry(entryRequest: EntryRequest): Boolean
    fun deleteEntry(entryRequest: EntryRequest): Boolean

}