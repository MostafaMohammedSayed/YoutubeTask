package com.example.android.youtubetask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseVideoInfo::class], version = 1, exportSchema = false)
abstract class VideoInfoDatabase : RoomDatabase() {
    abstract val videoInfoDao: VideoInfoDao
}

private lateinit var DATABASE_INSTANCE: VideoInfoDatabase

fun getVideoInfoDatabase(context: Context): VideoInfoDatabase {
    synchronized(VideoInfoDatabase::class.java) {
        if (!::DATABASE_INSTANCE.isInitialized) {
            DATABASE_INSTANCE = Room.databaseBuilder(
                context,
                VideoInfoDatabase::class.java,
                "videoInfoDatabase"
            )
                .build()
        }
        return DATABASE_INSTANCE
    }
}