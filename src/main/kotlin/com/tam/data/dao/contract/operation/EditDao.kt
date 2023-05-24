package com.tam.data.dao.contract.operation

interface EditDao<T> {
    fun update(element: T): Boolean
    fun delete(element: T): Boolean
}