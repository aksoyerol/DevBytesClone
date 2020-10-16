package com.erolaksoy.devbytesclone.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.erolaksoy.devbytesclone.database.DevBytesDatabase
import com.erolaksoy.devbytesclone.domain.DevByteModel
import com.erolaksoy.devbytesclone.enums.Status
import com.erolaksoy.devbytesclone.repository.NetworkRepository
import kotlinx.coroutines.launch

class FeedViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = NetworkRepository(DevBytesDatabase.getInstance(app))
    val playList: LiveData<List<DevByteModel>>
        get() = repo.videos

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    init {
        refreshDataFromRepo()
        //refreshDataFromNetwork()
    }

//    private fun refreshDataFromNetwork() {
//        viewModelScope.launch {
//            _status.value = Status.LOADING
//            try {
//                val response = DevByteNetworkBuilder.networkservice.getPlayList()
//                playList.postValue(response.asDevByteList())
//                _status.value = Status.LOADED
//            } catch (e: Exception) {
//                _status.value = Status.LOADING
//                println("HATAAAAAA" + e.localizedMessage)
//            }
//        }
//    }

    private fun refreshDataFromRepo() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                repo.refreshDataFromRepo()
            }catch (e : Exception){
                println("HATAAAAAAAAAAAAAAA ${e.localizedMessage}")
            }
            _status.value = Status.LOADED
        }
    }

}

@Suppress("UNCHECKED_CAST")
class FeedViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(application) as T
        }
        throw IllegalArgumentException("ERRORRRR ON FACTORYFEED")
    }

}
