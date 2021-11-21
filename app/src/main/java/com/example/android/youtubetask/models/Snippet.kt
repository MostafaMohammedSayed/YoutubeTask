package com.example.android.youtubetask.models

data class Snippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val thumbnails: Thumbnails
) {

}
