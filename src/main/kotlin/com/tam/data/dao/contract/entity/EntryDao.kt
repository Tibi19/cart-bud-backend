package com.tam.data.dao.contract.entity

import com.tam.data.dao.contract.operation.EditDao
import com.tam.data.dao.contract.operation.GetAllByParentDao
import com.tam.data.dao.contract.operation.GetByIdDao
import com.tam.data.dao.contract.operation.InsertDao
import com.tam.data.model.entity.Entry

interface EntryDao : InsertDao<Entry>, EditDao<Entry>, GetAllByParentDao<Entry>, GetByIdDao<Entry>