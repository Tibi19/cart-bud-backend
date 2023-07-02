package com.tam.data.dao.contract.operation

interface GetByIdsDao<T> {
    fun getByIds(ids: List<String>): List<T>?
}