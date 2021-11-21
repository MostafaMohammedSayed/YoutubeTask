package com.example.android.youtubetask.database

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room


@Entity
data class DatabaseVideoInfo(
    @PrimaryKey
    val title: String,
    val thumbnailUrl: String
) {
}

