package com.tam.data.util

import org.ktorm.entity.*
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.schema.Table

fun <E : Entity<E>, T : Table<E>> EntitySequence<E, T>.tryAdd(element: E): Boolean =
    tryOperation {
        add(element)
    }

fun <E : Entity<E>, T : Table<E>> EntitySequence<E, T>.tryUpdate(element: E): Boolean =
    tryOperation {
        update(element)
    }

fun <E : Entity<E>, T : Table<E>> EntitySequence<E, T>.tryRemoveIf(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean =
    tryOperation {
        removeIf(predicate)
    }

private inline fun tryOperation(operation: () -> Unit): Boolean =
    try {
        operation()
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

fun <T> tryGet(get: () -> T?): T? =
    try {
        get()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }