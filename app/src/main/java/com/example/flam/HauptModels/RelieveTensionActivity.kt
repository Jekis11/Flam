package com.example.flam.HauptModels

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import com.example.flam.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_relieve_tension.*
import java.util.regex.Pattern


class RelieveTensionActivity : YouTubeBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relieve_tension)

        initilizePlayer(getYoutubeVideofromUrl("https://www.youtube.com/watch?v=IUN664s7N-c")!!)
    }

    fun getYoutubeVideofromUrl(inUrl: String): String?{
        if (inUrl.toLowerCase().contains("youtu.be")){
            return inUrl.substring(inUrl.lastIndexOf("/")+1)
        }
        val pattern = "(?<=watch\\?v=|/video/|embed\\/)[^#\\&\\?]*"
        val compliedPattern = Pattern.compile(pattern)
        val matcher = compliedPattern.matcher(inUrl)
        return if(matcher.find()){
            matcher.group()
        }
        else null
    }

    private fun initilizePlayer(videoId :String) {
        youtubebeplayer.initialize(getString(R.string.apikeyyoutube),object :YouTubePlayer.OnInitializedListener
        {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1!!.loadVideo(videoId)
                p1.play()

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext,"error occured",Toast.LENGTH_SHORT).show()
            }

        })
    }
}