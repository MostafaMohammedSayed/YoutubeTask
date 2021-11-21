package com.example.android.youtubetask.network

import com.example.android.youtubetask.models.VideoInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl(NetworkConstants.BASE_URL)
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface YoutubeApiService {

//    @GET("users/{id}")
//    fun getUserById(@Path(value = "id") userID: Int): Observable<User>
//
//    @GET("users")
//    fun getUsers(): Observable<List<User>>
//
//    @GET("users/{id}/albums")
//    fun getUserAlbumsByUserId(@Path(value = "id") userID: Int): Observable<List<Album>>
//
//    @GET("albums")
//    fun getAllUsersAlbums(): Observable<List<Album>>

    @GET("videos?part=snippet&id=liJVSwOiiwg&key=AIzaSyDu3-9-oH6rWVm5c8AmN-xzyKDodH8VFFs")
    fun getVideoInfo(): Observable<VideoInfo>
}

object VideoApi {

    val retrofitService: YoutubeApiService by lazy {
        retrofit.create(YoutubeApiService::class.java)
    }
}