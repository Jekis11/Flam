package com.example.flam.HauptModels

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.example.flam.R

class RelieveTensionActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relieve_tension)
        val videoView : VideoView = findViewById(R.id.video)
        val mediaController: MediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setVideoPath("https://www.youtube.com/watch?v=IUN664s7N-c")
        videoView.start()
        videoView.setMediaController(mediaController)
    }
}