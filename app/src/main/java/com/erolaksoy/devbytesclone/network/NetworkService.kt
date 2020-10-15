package com.erolaksoy.devbytesclone.network

import com.erolaksoy.devbytesclone.domain.ApiResponseModel

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



interface NetworkService {
    @GET("devbytes")
    suspend fun getPlayList(): ApiResponseModel
}

object DevByteNetworkBuilder {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val networkservice = retrofit.create(NetworkService::class.java)
}