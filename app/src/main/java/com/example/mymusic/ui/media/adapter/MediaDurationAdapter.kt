package com.example.mymusic.ui.media.adapter

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymusic.R
import com.example.mymusic.models.Media

class MediaDurationAdapter : RecyclerView.Adapter<MediaDurationAdapter.MediaDurationViewHolder>() {
    var mediaList= mutableListOf<Media>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaDurationViewHolder {
        return MediaDurationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.media_duration_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MediaDurationViewHolder, position: Int) {
        Log.d("mediaAdapter","position $position")
        val media=mediaList[position]

        var mediaPlayer=MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC);
            setDataSource(media.soundUrl)
            prepareAsync()
            setOnPreparedListener {
                media.isPrepared=true
                holder.duration.text=it.duration.toString()
            }
        }

        if (media.isPlaying){
            holder.mediaImg.setImageResource(R.drawable.ic_voice_pause)
        }else{
            holder.mediaImg.setImageResource(R.drawable.ic_voice_play)
        }
        holder.bind(media,mediaPlayer)

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

    inner class MediaDurationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mediaImg=itemView.findViewById<ImageView>(R.id.playImgDuration)
        var duration=itemView.findViewById<TextView>(R.id.duration)
        var mediaPlayer: MediaPlayer?=null

        fun bind(media: Media , mediaPlayer: MediaPlayer) {
            this.mediaPlayer=mediaPlayer
//            if (media.isPlaying){
//                mediaImg.setImageResource(R.drawable.ic_voice_pause)
//            }else{
//                mediaImg.setImageResource(R.drawable.ic_voice_play)
//            }
            itemView.setOnClickListener {
                Log.d("mediaAdapter","dddd")
                MyMusicPlayer.playMediaWithDuration(this@MediaDurationAdapter,adapterPosition,media,mediaPlayer, mediaImg , media.isPrepared)
            }
        }

    }
}