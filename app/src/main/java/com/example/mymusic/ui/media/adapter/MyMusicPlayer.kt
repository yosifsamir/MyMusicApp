package com.example.mymusic.ui.media.adapter

import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.ImageView
import com.example.mymusic.R
import com.example.mymusic.models.Media

object MyMusicPlayer {
    private var musicPlayer: MediaPlayer? = null
    private var mediaImageView: ImageView? = null
    private var media:Media?=null
    private var prevPosition=-1

    fun playMediaWithNotify(mediaAdapter2: MediaAdapter2,position:Int,media: Media, musicPlayer: MediaPlayer?, mediaImageView: ImageView) {
        if (this.musicPlayer == null) {
            this.musicPlayer = musicPlayer
            this.media=media
            this.media?.isPlaying=true
            this.musicPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC);
                setDataSource(media.soundUrl)
                prepareAsync()
                setOnPreparedListener {
                    start()
                }
            }
//            this.mediaImageView = mediaImageView
            prevPosition=position
            mediaAdapter2.notifyItemChanged(prevPosition)
//            this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
        } else {
            this.media?.isPlaying=false
            this.musicPlayer?.stop()
            this.musicPlayer?.release()
            this.musicPlayer = null
            mediaAdapter2.notifyItemChanged(prevPosition)
//            this.mediaImageView?.setImageResource(R.drawable.ic_voice_play)
            this.musicPlayer = musicPlayer
            this.musicPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC);
                setDataSource(media.soundUrl)
                prepareAsync()
                setOnPreparedListener {
                    start()
                }
            }
            this.media=media
            this.media?.isPlaying=true
//            this.mediaImageView = mediaImageView
            prevPosition=position
            this.musicPlayer?.start()
            mediaAdapter2.notifyItemChanged(prevPosition)
//            this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
        }
    }

    fun playMediaWithViews(mediaAdapter2: MediaAdapter2,position:Int,media: Media, musicPlayer: MediaPlayer?, mediaImageView: ImageView) {
        if (this.musicPlayer == null) {
            this.musicPlayer = musicPlayer
            this.media=media
            this.media?.isPlaying=true
            this.musicPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC);
                setDataSource(media.soundUrl)
                prepareAsync()
                setOnPreparedListener {
                    start()
                }
            }
            this.mediaImageView = mediaImageView
            prevPosition=position
//            mediaAdapter2.notifyItemChanged(prevPosition)
            this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
        } else {
            this.media?.isPlaying=false
            this.musicPlayer?.stop()
            this.musicPlayer?.release()
            this.musicPlayer = null

//            mediaAdapter2.notifyItemChanged(prevPosition)
            this.mediaImageView?.setImageResource(R.drawable.ic_voice_play)

            if (position!= prevPosition){
                this.musicPlayer = musicPlayer
                this.musicPlayer = MediaPlayer().apply {
                    setAudioStreamType(AudioManager.STREAM_MUSIC);
                    setDataSource(media.soundUrl)
                    prepareAsync()
                    setOnPreparedListener {
                        start()
                    }
                }
                this.media=media
                this.media?.isPlaying=true
                this.mediaImageView = mediaImageView
                prevPosition=position
                this.musicPlayer?.start()
//            mediaAdapter2.notifyItemChanged(prevPosition)
                this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
            }

        }
    }

    fun playMediaWithDuration(mediaAdapter2: MediaDurationAdapter,position:Int,media: Media, musicPlayer: MediaPlayer?, mediaImageView: ImageView , isPrepared:Boolean) {
        if (this.musicPlayer == null) {
            this.musicPlayer = musicPlayer
            this.media=media
            this.media?.isPlaying=true
            this.musicPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC);
                setDataSource(media.soundUrl)
                prepareAsync()
                setOnPreparedListener {
                    start()
                }
            }
            this.mediaImageView = mediaImageView
            prevPosition=position
            this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
        } else {
            if (this.musicPlayer==null){
                this.musicPlayer=musicPlayer
            }
            this.media=media
            this.media?.isPlaying=false
            this.media?.isPrepared=false
            this.musicPlayer?.stop()
//            this.musicPlayer?.release()
//            this.musicPlayer = null  // Todo("")

            this.mediaImageView?.setImageResource(R.drawable.ic_voice_play)

            if (position!= prevPosition){
                this.musicPlayer=musicPlayer
                if (isPrepared){

                    this.media=media
                    this.media?.isPlaying=true
                    this.mediaImageView = mediaImageView
                    prevPosition=position
//                this.musicPlayer?.start()
                    this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
                    this.musicPlayer?.start()
                }else{
                    this.musicPlayer?.prepareAsync()
                    this.musicPlayer?.setOnPreparedListener{

                        this.media=media
                        this.media?.isPlaying=true
                        this.mediaImageView = mediaImageView
                        prevPosition=position
//                this.musicPlayer?.start()
                        this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
                        this.musicPlayer?.start()
                    }
                }

//                this.musicPlayer = musicPlayer
//                this.musicPlayer = MediaPlayer().apply {
//                    setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    setDataSource(media.soundUrl)
//                    prepareAsync()
//                    setOnPreparedListener {
//                        start()
//                    }
//                }

//                this.media=media
//                this.media?.isPlaying=true
//                this.mediaImageView = mediaImageView
//                prevPosition=position
//                this.mediaImageView?.setImageResource(R.drawable.ic_voice_pause)
            }

        }
    }
}