package com.tam.data.dao.contract.entity

import com.tam.data.dao.contract.operation.*
import com.tam.data.model.entity.Entry

interface EntryDao :
    InsertDao<Entry>,
    EditDao<Entry>,
    GetAllByParentDao<Entry>,
    GetByIdDao<Entry>,
    GetByIdsDao<Entry>