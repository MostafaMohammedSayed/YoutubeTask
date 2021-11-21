package com.example.android.youtubetask.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android.youtubetask.R
import com.example.android.youtubetask.database.DatabaseVideoInfo
import com.squareup.picasso.Picasso

class VideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val videos = ArrayList<DatabaseVideoInfo>()

    override fun getItemViewType(position: Int): Int {
        return if (videos.size == 0) {
            PLACE_HOLDER_VIEW_TYPE
        } else {
            ITEM_HOLDER_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PLACE_HOLDER_VIEW_TYPE -> LoadingViewHolder.from(parent)
            else -> VideoHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VideoHolder) {
            val video = videos[position]
            holder.bind(video)
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return if (videos.size == 0) {
            1
        } else {
            videos.size
        }
    }

    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.videoTitle)
        private val imgView = itemView.findViewById<ImageView>(R.id.thumbnail)
        fun bind(databaseVideoInfo: DatabaseVideoInfo) {
            textView.text = databaseVideoInfo.title
            Picasso.with(itemView.context).load(databaseVideoInfo.thumbnailUrl).into(imgView)
        }

        companion object {
            fun from(parent: ViewGroup): VideoHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val inflatedView =
                    layoutInflater.inflate(R.layout.item_youtube_video, parent, false)
                return VideoHolder(inflatedView)
            }
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.shimmerVideoTitle)
        private val imgView = itemView.findViewById<ImageView>(R.id.shimmerThumbnail)
        fun bind() {
            textView.background =
                ContextCompat.getDrawable(itemView.context, R.color.place_holder_background_color)

            imgView.background =
                ContextCompat.getDrawable(itemView.context, R.color.place_holder_background_color)
        }

        companion object {
            fun from(parent: ViewGroup): LoadingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val inflatedView = layoutInflater.inflate(R.layout.shimmer_layout, parent, false)
                return LoadingViewHolder(inflatedView)
            }
        }
    }

    companion object {
        const val PLACE_HOLDER_VIEW_TYPE = 0
        const val ITEM_HOLDER_VIEW_TYPE = 1
    }
}