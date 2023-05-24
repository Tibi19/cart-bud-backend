package com.tam.data.dao.contract.operation

interface InsertDao<T> {
    fun insert(element: T): Boolean
}