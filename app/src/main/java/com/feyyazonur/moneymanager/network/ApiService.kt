package com.feyyazonur.moneymanager.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Example Request: https://v6.exchangerate-api.com/v6/414487a52c0a88a859376e4a/latest/TRY
private const val API_KEY = "414487a52c0a88a859376e4a"
private const val BASE_URL = "https://v6.exchangerate-api.com/v6/${API_KEY}/latest/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("TRY")
    fun getKurTRY(): Call<ApiResponse>
}

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}