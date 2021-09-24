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
        var videoView : VideoView = findViewById(R.id.video)
        val mediaController: MediaController
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setVideoPath("https://www.youtube.com/watch?v=AIHXRSYW2CI")
        videoView.start()
        videoView.setMediaController(mediaController)
    }
}