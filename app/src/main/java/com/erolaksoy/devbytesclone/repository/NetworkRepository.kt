package com.erolaksoy.devbytesclone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.erolaksoy.devbytesclone.database.DevBytesDatabase
import com.erolaksoy.devbytesclone.database.asDevByteModelList
import com.erolaksoy.devbytesclone.domain.DevByteModel
import com.erolaksoy.devbytesclone.domain.asDatabaseList
import com.erolaksoy.devbytesclone.domain.asDevByteList
import com.erolaksoy.devbytesclone.network.DevByteNetworkBuilder
import com.erolaksoy.devbytesclone.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(private val database: DevBytesDatabase) {
    val videos: LiveData<List<DevByteModel>> = Transformations.map(database.devBytesDao.getAll()) {
        it.asDevByteModelList()
    }

    suspend fun refreshDataFromRepo() {
        withContext(Dispatchers.IO){
            val networkPlayList = DevByteNetworkBuilder.networkservice.getPlayList()
            database.devBytesDao.insertAll(networkPlayList.asDatabaseList())
        }
    }
}

