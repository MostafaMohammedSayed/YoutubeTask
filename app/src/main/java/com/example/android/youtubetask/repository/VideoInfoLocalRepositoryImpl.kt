package com.example.android.youtubetask.repository

import com.example.android.youtubetask.App
import com.example.android.youtubetask.database.DatabaseUtils.Companion.modelToDatabaseModel
import com.example.android.youtubetask.database.DatabaseVideoInfo
import com.example.android.youtubetask.database.getVideoInfoDatabase
import com.example.android.youtubetask.models.VideoInfo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class VideoInfoLocalRepositoryImpl: VideoInfoRepository {
    val context = App.applicationContext()
    val vIDatabase = getVideoInfoDatabase(context)
    override fun getVideoInfoFromRemote(): Observable<VideoInfo> {
        throw IllegalStateException("should be called from remote repo only")
    }

    override fun storeVideoInfo(videoInfo: VideoInfo) {
        val datbaseVideoInfo = modelToDatabaseModel(videoInfo)
        vIDatabase.videoInfoDao.saveVideoInfo(datbaseVideoInfo)
    }

    override fun getVideoInfoFromLocal(): Observable<DatabaseVideoInfo> {
        return vIDatabase.videoInfoDao.getAllVideoInfo()
    }
}