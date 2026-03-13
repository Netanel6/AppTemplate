package com.example.app.core.testing.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

suspend fun <T> Flow<T>.awaitFirst(): T = first()
