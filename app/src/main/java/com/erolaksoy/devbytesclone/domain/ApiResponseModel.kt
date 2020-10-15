package com.erolaksoy.devbytesclone.domain

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiResponseModel(val videos: List<ApiVideosModel>)

@JsonClass(generateAdapter = true)
data class ApiVideosModel(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
)

fun ApiResponseModel.asDevByteList(): List<DevByteModel> {
    return videos.map {
        DevByteModel(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}