package com.example.android.youtubetask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.android.youtubetask.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private val videosAdapter = VideoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpViewModel()
        setUpViews()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.observeVideoInfoLiveData(this,{videoInfo->
            Log.i(this.javaClass.simpleName,videoInfo.toString())
        },
            { databaseVideosInfo->
                Log.i(this.javaClass.simpleName,databaseVideosInfo.isEmpty().toString())
                videosAdapter.videos.addAll(databaseVideosInfo)
                videosAdapter.notifyDataSetChanged()
            })
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun setUpViews() {
        val itemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rvVideos.apply {
            adapter = videosAdapter
            addItemDecoration(itemDecoration)
        }
    }
}