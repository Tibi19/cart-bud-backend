package com.tam.data.dao.contract.operation

interface GetAllByParentDao<T> {
    fun getAllByParent(parentId: String): List<T>?
}