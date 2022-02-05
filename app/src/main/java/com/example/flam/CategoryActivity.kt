package com.example.flam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_details.*

class CategoryActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)



        backpressed.setOnClickListener {
            val intent = Intent(this,HauptActivity::class.java)
            startActivity(intent)
        }

        drums1.setOnClickListener {
            val intent = Intent(this,NavCategoryActivity::class.java)
            startActivity(intent)
        }

    }
}