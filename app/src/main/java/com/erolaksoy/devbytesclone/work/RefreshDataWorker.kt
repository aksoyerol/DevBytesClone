package com.erolaksoy.devbytesclone.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.erolaksoy.devbytesclone.database.DevBytesDatabase
import com.erolaksoy.devbytesclone.repository.NetworkRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
    const val WORK_NAME = "com.erolaksoy.devbytesclone.work.RefresDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = DevBytesDatabase.getInstance(applicationContext)
        val repo = NetworkRepository(database)
        try {
            repo.refreshDataFromRepo()
            Timber.d("Work request is run!")
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }

}