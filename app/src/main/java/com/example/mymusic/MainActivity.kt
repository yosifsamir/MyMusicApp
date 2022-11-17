package com.example.mymusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymusic.databinding.ActivityMainBinding
import com.example.mymusic.ui.media.MediaFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrame.id,MediaFragment())
            .commit()
    }
}