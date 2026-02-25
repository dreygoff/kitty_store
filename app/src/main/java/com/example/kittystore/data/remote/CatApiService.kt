package com.example.kittystore.data.remote

import com.example.kittystore.BuildConfig
import com.example.kittystore.data.dto.CatDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatApiService {

    @GET("v1/images/search")
    suspend fun getCats(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Header("x-api-key") apiKey: String = BuildConfig.CAT_API_KEY
    ): List<CatDto>
}