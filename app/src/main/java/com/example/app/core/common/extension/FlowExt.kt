package com.example.app.core.common.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> T.asFlow(): Flow<T> = flow { emit(this@asFlow) }
