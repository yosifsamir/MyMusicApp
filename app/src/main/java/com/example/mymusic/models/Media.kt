package com.example.mymusic.models

data class Media (
    var isPlaying:Boolean=false,
    val soundUrl:String,
    var isPrepared:Boolean = false
)