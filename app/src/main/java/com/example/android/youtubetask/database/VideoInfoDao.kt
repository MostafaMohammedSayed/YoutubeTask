package com.example.android.youtubetask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable

@Dao
interface VideoInfoDao {
    @Insert
    fun saveVideoInfo(databaseVideoInfo: DatabaseVideoInfo)

    @Query("Select * From databasevideoinfo")
    fun getAllVideoInfo(): Observable<DatabaseVideoInfo>

    @Query("Select * From databasevideoinfo Where title =:wantedTitle")
    fun getVideoInfoByTitle(wantedTitle: String): Observable<DatabaseVideoInfo>

}