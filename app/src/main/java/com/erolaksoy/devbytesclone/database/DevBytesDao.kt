package com.erolaksoy.devbytesclone.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erolaksoy.devbytesclone.domain.DevByteModel

@Dao
interface DevBytesDao {

    @Query("SELECT * FROM devBytesDb")
    fun getAll(): LiveData<List<DevBytesDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<DevBytesDatabaseModel>)

    @Query("DELETE FROM devBytesDb")
    fun deleteAll()

    @Update
    fun update(model: DevBytesDatabaseModel)
}