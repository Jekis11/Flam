package com.example.flam

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.flam.adapter.ViewPageAdapter
import com.example.flam.models.ViewAll
import kotlinx.android.synthetic.main.activity_details.*
import me.relex.circleindicator.CircleIndicator3








class DetailsActivity : AppCompatActivity() {
    private lateinit var imagedetails : ImageView
    private lateinit var addItem : ImageView
    private lateinit var removeItem : ImageView
    private lateinit var price : TextView
    private lateinit var rating : TextView
    private lateinit var description : TextView
    private lateinit var addtoCart : Button
    private  var viewAll : ViewAll? = null




    private var imageList = mutableListOf<Int>()
    private lateinit var toolbar : androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val `object`: Any? = intent.getSerializableExtra("detail")
        if (`object` is ViewAll) {
            viewAll = `object`
        }

        imagedetails = findViewById(R.id.detail_img)
        addItem = findViewById(R.id.add_item)
        removeItem = findViewById(R.id.delete_item)
        addtoCart = findViewById(R.id.buttonaddtocart)
        price = findViewById(R.id.detail_price)
        rating = findViewById(R.id.detailrating)
        description = findViewById(R.id.descriptiondetail)

        if(viewAll != null) {
            Glide.with(applicationContext).load(viewAll!!.img_url).into(detail_img)
            rating.setText(viewAll!!.rating)
            description.setText(viewAll!!.description)
            price.setText("Price :€" + viewAll!!.price)

        }


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