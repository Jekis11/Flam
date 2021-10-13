package com.example.flam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import dev.yuganshtyagi.smileyrating.SmileyRatingView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rateus.*

class RateUs : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rateus)

        rating_bar.setOnRatingBarChangeListener { _, rating, _ ->
            smiley_view.setSmiley(rating = rating)
        }


    }
}

