package com.tam.data.dao.contract.operation

interface DeleteDao {
    fun delete(elementId: String): Boolean
}