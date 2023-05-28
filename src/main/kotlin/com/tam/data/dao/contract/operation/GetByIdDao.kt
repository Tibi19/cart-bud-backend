package com.tam.data.dao.contract.operation

interface GetByIdDao<T> {
    fun getById(id: String): T?
}