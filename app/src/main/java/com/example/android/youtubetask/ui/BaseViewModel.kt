package com.example.android.youtubetask.ui

import androidx.lifecycle.ViewModel
import com.example.android.youtubetask.repository.VideoInfoLocalRepositoryImpl
import com.example.android.youtubetask.repository.VideoInfoRemoteRepositoryImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {
    val mCompositeDisposable = CompositeDisposable()
    val videoRemoteRepo = VideoInfoRemoteRepositoryImpl()
    val videoLocaleRepo = VideoInfoLocalRepositoryImpl()


    override fun onCleared() {
        mCompositeDisposable.clear()
        super.onCleared()
    }
}