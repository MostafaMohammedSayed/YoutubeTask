package com.example.android.youtubetask.ui

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.android.youtubetask.database.DatabaseVideoInfo
import com.example.android.youtubetask.models.VideoInfo
import com.example.android.youtubetask.repository.VideoInfoRepositoryImpl
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {
    private val allVideoInfoLiveData = MutableLiveData<List<DatabaseVideoInfo>>()
    private val repo = VideoInfoRepositoryImpl()


    private fun fetchVideoInfo() {

        repo.getVideoInfoFromRemote()
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : io.reactivex.rxjava3.core.Observer<VideoInfo> {
                override fun onSubscribe(d: Disposable) {
                    Log.i("HomeViewModel", "Subscription Started")
                }

                override fun onNext(vInfo: VideoInfo) {
                    repo.storeVideoInfo(vInfo)
                }

                override fun onError(error: Throwable) {
                    Log.e("HomeViewModel", error.message.toString(), error)
                }

                override fun onComplete() {
                    Log.i("HomeViewModel", "Subscription Completed")
                }

            })
    }

    private fun fetchDataFromDatabase() {

        repo.getAllVideoInfoFromLocal()
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                io.reactivex.rxjava3.core.Observer<List<DatabaseVideoInfo>> {
                override fun onSubscribe(d: Disposable) {
                    Log.i("HomeViewModel", "Second Subscription Started")
                }

                override fun onNext(videoList: List<DatabaseVideoInfo>) {
                    try {
                        allVideoInfoLiveData.postValue(videoList)
                    } catch (error: Exception) {
                        Log.e("HomeViewModel", error.message.toString(), error)

                    }
                }

                override fun onError(error: Throwable) {
                    Log.e("HomeViewModel", error.message.toString(), error)
                }

                override fun onComplete() {
                    Log.i("HomeViewModel", "Second Subscription Completed")
                }

            })

    }

    fun fetchData(){
        fetchDataFromDatabase()
        fetchVideoInfo()
    }


    fun observeAllVideosInfoLiveData(
        owner: LifecycleOwner,
        allObserver: Observer<List<DatabaseVideoInfo>>
    ) {
        allVideoInfoLiveData.observe(owner, allObserver)
    }
}