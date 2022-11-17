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

class MediaAdapter() : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {
    var mediaList= mutableListOf<Media>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.media_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        Log.d("mediaAdapter",position.toString())
        val media=mediaList[position]
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

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mediaImg=itemView.findViewById<ImageView>(R.id.playImg)
        var mediaPlayer:MediaPlayer?=null

        fun bind(media: Media) {
            if (media.isPlaying){
                mediaImg.setImageResource(R.drawable.ic_voice_pause)
                if (mediaPlayer!=null){
                    if (mediaPlayer?.isPlaying == true){

                    }
                }else {
                    mediaPlayer=MediaPlayer().apply {
                        setAudioStreamType(AudioManager.STREAM_MUSIC);
                        setDataSource(media.soundUrl)
                        prepareAsync()
                        setOnPreparedListener {
                            start()
                        }
                    }

                }

            }else{
                mediaImg.setImageResource(R.drawable.ic_voice_play)
                Log.d("mediaAdapter","prev --- $prevPosition")
                Log.d("mediaAdapter","adapterPosition --- $adapterPosition")
                Log.d("mediaAdapter","mediaPlayer --- ${mediaPlayer}")
                if (mediaPlayer!=null){
                    Log.d("mediaAdapter","prev --- $prevPosition")
                    Log.d("mediaAdapter","adapterPosition --- $adapterPosition")
//                    if (prevPosition==adapterPosition){
//                        Log.d("mediaAdapter","prev == Adapter then stop release")
//                        mediaPlayer?.stop()
//                        mediaPlayer?.release()
//                        mediaPlayer=null
//                    }
//                    else{
//                        if(!mediaPlayer?.isPlaying!!){
                            mediaPlayer?.stop()
                            mediaPlayer?.release()
                            mediaPlayer=null
//                        }
//                    }

                }
            }

            itemView.setOnClickListener {
                Log.d("mediaAdapter","dddd")
//            media.isPlaying=media.isPlaying.not()
//            if (media.isPlaying){
//                holder.mediaImg.setImageResource(R.drawable.ic_voice_pause)
//            }else{
//                holder.mediaImg.setImageResource(R.drawable.ic_voice_play)
//            }
                if (prevPosition==adapterPosition){
                    Log.d("mediaAdapter","${prevPosition} and is equal adapter position")


                }else if(prevPosition!=-1){
                    Log.d("mediaAdapter","${prevPosition} != -1")
                    // stop previous position first

                    Log.d("mediaAdapter","mediaList[prevPosition!!].isPlaying =false\n" +
                            "                    notifyItemChanged(prevPosition!!) is called")
                    mediaList[prevPosition!!].isPlaying =false
                    notifyItemChanged(prevPosition!!)


                    // play next one

                    prevPosition=adapterPosition
                    media.isPlaying=true
                    notifyItemChanged(prevPosition!!)
                    Log.d("mediaAdapter","prevPosition=adapterPosition\n" +
                            "                    media.isPlaying=true\n" +
                            "                    notifyItemChanged(adapterPosition) is called")



                }else{
                    Log.d("mediaAdapter","${prevPosition} is -1")
                    prevPosition=adapterPosition
                    media.isPlaying=true
                    notifyItemChanged(prevPosition)
                }

            }
        }

    }
}