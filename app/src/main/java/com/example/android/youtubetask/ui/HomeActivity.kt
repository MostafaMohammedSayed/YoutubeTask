package com.example.android.youtubetask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.android.youtubetask.R
import com.example.android.youtubetask.utils.Resource
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private val videosAdapter = VideoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpViewModel()
        setUpViews()
        fetchUiData()
        setUpObservers()
    }

    private fun fetchUiData() {
        viewModel.fetchData()
    }

    private fun setUpObservers() {
        viewModel.observeAllVideosInfoLiveData(this,
            { databaseVideoInfoResource->
                Log.i(this.javaClass.simpleName,databaseVideoInfoResource.value.toString())

                if (databaseVideoInfoResource.state == Resource.State.SUCCESS){
                    databaseVideoInfoResource.value?.let { videosAdapter.videos.add(it) }
                    videosAdapter.notifyDataSetChanged()
                }
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