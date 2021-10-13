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

    lateinit var editTextMail: EditText
    lateinit var editTextSubject: EditText
    lateinit var editTextMessage: EditText
    lateinit var buttonSend: Button
    lateinit var email: String
    lateinit var subject: String
    lateinit var message: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rateus)

        rating_bar.setOnRatingBarChangeListener { _, rating, _ ->
            smiley_view.setSmiley(rating = rating)
        }


        editTextMail = findViewById(R.id.editTextMail)
        editTextSubject = findViewById(R.id.rating_bar)
        editTextMessage = findViewById(R.id.review_box)




        var button = findViewById<Button>(R.id.buttonsubmit)
        button.setOnClickListener {
            getData()
            val intent = Intent(Intent.ACTION_SEND)
            editTextMail = findViewById(R.id.editTextMail)
            editTextSubject = findViewById(R.id.rating_bar)
            editTextMessage = findViewById(R.id.review_box)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Select email"))
        }
    }

        private fun getData() {
            email = editTextMail.text.toString()
            subject = editTextSubject.text.toString()
            message = editTextMessage.text.toString()

        }



    }

