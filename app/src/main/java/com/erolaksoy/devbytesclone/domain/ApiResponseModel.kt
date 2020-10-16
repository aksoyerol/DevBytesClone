package com.erolaksoy.devbytesclone.domain

import com.erolaksoy.devbytesclone.database.DevBytesDatabaseModel
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

fun ApiResponseModel.asDatabaseList(): List<DevBytesDatabaseModel> {
    return videos.map {
        DevBytesDatabaseModel(
            description = it.description,
            thumbnail = it.thumbnail,
            updated = it.updated,
            url = it.url,
            title = it.title
        )
    }
}