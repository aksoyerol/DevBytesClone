package com.erolaksoy.devbytesclone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DevBytesDatabaseModel::class], exportSchema = false, version = 1)
abstract class DevBytesDatabase : RoomDatabase() {
    abstract val devBytesDao: DevBytesDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: DevBytesDatabase
        fun getInstance(context: Context): DevBytesDatabase {
            synchronized(DevBytesDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DevBytesDatabase::class.java,
                        "devBytesDb"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

    }
}



