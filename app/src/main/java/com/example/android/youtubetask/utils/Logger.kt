package com.example.android.youtubetask.utils

import android.util.Log

class Logger {
    fun Any.debug(any: Any?){
        Log.d(this.javaClass.simpleName,any.toString())
    }

    fun Any.debugError(error: Throwable){
        Log.e(this.javaClass.simpleName,error.message.toString(), error)
    }
}