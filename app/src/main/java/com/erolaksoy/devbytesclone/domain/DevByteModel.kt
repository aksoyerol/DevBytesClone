package com.erolaksoy.devbytesclone.domain


data class DevByteModel(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
) {
}

//data class Items(@field:SerializedName("videos") val items : List<DevByteModel>)
