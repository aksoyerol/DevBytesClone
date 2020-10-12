package com.erolaksoy.devbytesclone.network

import com.erolaksoy.devbytesclone.domain.DevByteModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface NetworkService {
    @GET("devbytes")
    suspend fun getPlayList(): List<DevByteModel>
}

object DevByteNetworkBuilder {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val networkservice = retrofit.create(NetworkService::class.java)
}