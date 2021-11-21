package com.example.android.youtubetask.repository

import com.example.android.youtubetask.database.DatabaseVideoInfo
import com.example.android.youtubetask.models.VideoInfo
import io.reactivex.rxjava3.core.Observable

interface VideoInfoRepository {
    fun getVideoInfoFromRemote(): Observable<VideoInfo>
    fun storeVideoInfo(videoInfo: VideoInfo)
    fun getVideoInfoFromLocal(): Observable<DatabaseVideoInfo>
}