package com.tam.usecase

fun doChangesExist(vararg changes: Changes<*>) =
    changes.any {
        it.doChangesExist()
    }

private fun <T> Changes<T>.doChangesExist() =
    updated.isNotEmpty() || deletedIds.isNotEmpty()