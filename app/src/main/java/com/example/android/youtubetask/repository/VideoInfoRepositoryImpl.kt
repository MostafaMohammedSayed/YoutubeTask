package com.example.android.youtubetask.repository

import com.example.android.youtubetask.database.DatabaseVideoInfo
import com.example.android.youtubetask.models.VideoInfo
import io.reactivex.rxjava3.core.Observable

class VideoInfoRepositoryImpl: VideoInfoRepository {
    val remoRepo = VideoInfoRemoteRepositoryImpl()
    val cashRepo = VideoInfoLocalRepositoryImpl()
    override fun getVideoInfoFromRemote(): Observable<VideoInfo> {
        return remoRepo.getVideoInfoFromRemote()
    }

    override fun storeVideoInfo(videoInfo: VideoInfo) {
        cashRepo.storeVideoInfo(videoInfo)
    }

    override fun getVideoInfoFromLocal(): Observable<DatabaseVideoInfo> {
        return cashRepo.getVideoInfoFromLocal()
    }
}