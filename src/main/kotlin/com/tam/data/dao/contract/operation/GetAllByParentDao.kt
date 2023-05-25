package com.tam.data.dao.contract.operation

interface GetAllByParentDao<T> {
    fun getAllByParentId(parentId: String): List<T>?
}