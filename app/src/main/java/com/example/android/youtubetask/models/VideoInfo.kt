package com.example.android.youtubetask.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class VideoInfo(
    val kind: String,
    val etag: String,
    val items: List<Item>
) {
}