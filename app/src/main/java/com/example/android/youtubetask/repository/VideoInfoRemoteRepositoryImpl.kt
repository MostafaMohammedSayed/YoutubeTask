package com.example.android.youtubetask.repository

import com.example.android.youtubetask.database.DatabaseVideoInfo
import com.example.android.youtubetask.models.VideoInfo
import com.example.android.youtubetask.network.VideoApi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class VideoInfoRemoteRepositoryImpl : VideoInfoRepository {
    override fun getVideoInfoFromRemote(): Observable<VideoInfo> {
        return VideoApi.retrofitService.getVideoInfo()
            .subscribeOn(Schedulers.io())
    }

    override fun storeVideoInfo(videoInfo: VideoInfo) {
        throw IllegalStateException("should be called from local repo only")
    }

    override fun getAllVideoInfoFromLocal(): Observable<List<DatabaseVideoInfo>> {
        throw IllegalStateException("should be called from local repo only")
    }
}