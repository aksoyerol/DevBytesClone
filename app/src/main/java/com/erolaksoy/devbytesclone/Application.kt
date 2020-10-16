package com.erolaksoy.devbytesclone

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.erolaksoy.devbytesclone.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun delayedInit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
//        val repeatingRequest =
//            PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS).build()
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(15,TimeUnit.MINUTES).build()
        Timber.d("Periodic Work request for sync is scheduled")
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}