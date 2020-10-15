package com.erolaksoy.devbytesclone.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.erolaksoy.devbytesclone.domain.DevByteModel
import com.erolaksoy.devbytesclone.domain.asDevByteList
import com.erolaksoy.devbytesclone.network.DevByteNetworkBuilder
import com.erolaksoy.devbytesclone.repository.NetworkRepository
import kotlinx.coroutines.launch

class FeedViewModel() : ViewModel() {
    val playList = MutableLiveData<List<DevByteModel>>()


    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
      viewModelScope.launch {
          try{
              val response = DevByteNetworkBuilder.networkservice.getPlayList()
              playList.postValue(response.asDevByteList())
             // println("erol erol erol erol erol ${DevByteNetworkBuilder.networkservice.getPlayList().asDevByteList().size}")
          }catch (e: Exception){

              println("HATAAAAAA" + e.localizedMessage)
          }
      }
    }

}


