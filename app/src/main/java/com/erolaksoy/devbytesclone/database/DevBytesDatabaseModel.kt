package com.erolaksoy.devbytesclone.database

import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erolaksoy.devbytesclone.domain.DevByteModel

@Entity(tableName = "devBytesDb")
data class DevBytesDatabaseModel(
    @PrimaryKey
    val url: String,
    val title: String,
    val description: String,
    val updated: String,
    val thumbnail: String
) {
}

fun List<DevBytesDatabaseModel>.asDevByteModelList() : List<DevByteModel>{
    return map{
        DevByteModel(
            title = it.title,
            description = it.description,
            url = it.url,
            thumbnail = it.thumbnail,
            updated = it.updated
        )
    }
}