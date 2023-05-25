package com.tam.data.dao.contract.operation

interface UpdateDao<T> {
    fun update(element: T): Boolean
}