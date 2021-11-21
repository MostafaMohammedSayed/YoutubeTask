package com.example.android.youtubetask

import android.app.Application
import android.content.Context

class App: Application() {
    lateinit var context: Context
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = App.applicationContext()
    }
}