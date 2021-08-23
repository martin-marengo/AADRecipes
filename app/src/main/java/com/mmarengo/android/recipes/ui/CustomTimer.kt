package com.mmarengo.android.recipes.ui

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach

private const val ZERO_SEC = 0
private const val ONE_SEC = 1
private const val ONE_SEC_MS = 1_000L

fun initCountDown(totalSeconds: Int): Flow<Int> {
    return (totalSeconds - ONE_SEC downTo ZERO_SEC).asFlow()
        .onEach { delay(ONE_SEC_MS) }
        .conflate()
}
