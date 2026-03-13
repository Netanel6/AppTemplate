package com.example.app.core.network.api

import com.example.app.core.network.model.SampleItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SampleApiService {
    @GET("photos")
    suspend fun getSampleItems(@Query("_limit") limit: Int = 20): List<SampleItemDto>

    @GET("photos/{id}")
    suspend fun getSampleItem(@Path("id") id: Long): SampleItemDto
}
