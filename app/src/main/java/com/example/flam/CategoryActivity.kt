package com.example.flam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

   // onbackpressed..

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        backpressed.setOnClickListener {
            val intent = Intent(this,HauptActivity::class.java)
            startActivity(intent)
        }

    }
}