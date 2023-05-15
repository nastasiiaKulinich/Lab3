package com.example.numbers.data.cloud

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NumbersApi {

    @Headers("Content-Type: application/json")
    @GET("{id}")
    suspend fun fact(@Path("id") id: String): JsonObject

    @Headers("Content-Type: application/json")
    @GET("random/math")
    suspend fun random(): JsonObject

}