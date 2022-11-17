package com.example.mymusic.ui.media.adapter

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymusic.R
import com.example.mymusic.models.Media

class MediaAdapter2 : RecyclerView.Adapter<MediaAdapter2.MediaViewHolder2>() {
    var mediaList= mutableListOf<Media>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder2 {
        return MediaViewHolder2(LayoutInflater.from(parent.context).inflate(R.layout.media_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MediaViewHolder2, position: Int) {
        Log.d("mediaAdapter","position $position")
        val media=mediaList[position]
        if (media.isPlaying){
            holder.mediaImg.setImageResource(R.drawable.ic_voice_pause)
        }else{
            holder.mediaImg.setImageResource(R.drawable.ic_voice_play)
        }
        holder.bind(media)

    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    fun setData(mediaList:MutableList<Media>){
        this.mediaList=mediaList
        notifyDataSetChanged()
    }

    fun addData(mediaList:MutableList<Media>){
        this.mediaList.addAll(mediaList)
        notifyDataSetChanged()
    }

    var prevPosition:Int=-1

    inner class MediaViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mediaImg=itemView.findViewById<ImageView>(R.id.playImg)
        var mediaPlayer: MediaPlayer?=null

        fun bind(media: Media) {

//            if (media.isPlaying){
//                mediaImg.setImageResource(R.drawable.ic_voice_pause)
//            }else{
//                mediaImg.setImageResource(R.drawable.ic_voice_play)
//            }
            itemView.setOnClickListener {
                Log.d("mediaAdapter","dddd")
                MyMusicPlayer.playMediaWithViews(this@MediaAdapter2,adapterPosition,media,mediaPlayer, mediaImg)
            }
        }

    }
}