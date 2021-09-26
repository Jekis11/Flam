package com.example.flam.HauptModels

import android.annotation.SuppressLint
import android.net.Uri
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
        var videoView : VideoView = findViewById(R.id.videoview)
        val mediaController : MediaController
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setVideoPath(Uri.parse("https://www.youtube.com/watch?v=IUN664s7N-c&t=2s").toString())
        videoView.start()
        videoView.setMediaController(mediaController)
    }
}