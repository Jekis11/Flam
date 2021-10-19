package com.example.flam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.flam.HauptModels.ViewPagerAdapter
import com.example.flam.adapter.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_details.*
import me.relex.circleindicator.CircleIndicator3

class DetailsActivity : AppCompatActivity() {

    private var imageList = mutableListOf<Int>()
    private lateinit var toolbar : androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        postTolist()

        viewpagerdetails.adapter = ViewPageAdapter(imageList)
        viewpagerdetails.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(viewpagerdetails)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun addToList(image: Int){
        imageList.add(image)
    }

    private fun postTolist(){
        for (i in 1..3){
            addToList(R.mipmap.ic_launcher_round)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}