package com.example.android.youtubetask.database

import com.example.android.youtubetask.models.VideoInfo

class DatabaseUtils {
    companion object{
        fun modelToDatabaseModel(videoInfo: VideoInfo): DatabaseVideoInfo{
            return DatabaseVideoInfo(
                title = videoInfo.items[0].snippet.title,
                thumbnailUrl = videoInfo.items[0].snippet.thumbnails.standard.url
            )
        }
    }
}