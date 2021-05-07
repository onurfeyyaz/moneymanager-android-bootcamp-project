package com.feyyazonur.moneymanager.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"//"https://free.currconv.com/api/v7"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("photos")//?q=USD_TRY&compact=ultra&apiKey=97f79d770b768c4da66f")
    suspend fun getPhotos(): List<KurDeger>
}

object Api{
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}