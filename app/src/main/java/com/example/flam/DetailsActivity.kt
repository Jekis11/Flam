package com.example.flam

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.flam.adapter.ViewPageAdapter
import com.example.flam.models.ViewAll
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_details.*
import me.relex.circleindicator.CircleIndicator3
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class DetailsActivity :  AppCompatActivity() {
    private lateinit var detailimg : ImageView
    private lateinit var addItem : ImageView
    private lateinit var removeItem : ImageView
    private lateinit var price : TextView
    private lateinit var rating : TextView
    private lateinit var description : TextView
    private lateinit var quantity : TextView
    private var totalquantity: Int = 1
    private var totalPrice: Int = 0
    private lateinit var addtoCart : Button

    private lateinit var auth : FirebaseAuth
    private lateinit var firestore : FirebaseFirestore
    private  var viewAll : ViewAll? = null


    private var imageList = mutableListOf<Int>()
    private lateinit var toolbar : androidx.appcompat.widget.Toolbar

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val `object`: Any? = intent.getSerializableExtra("detail")
        if (`object` is ViewAll) {
            viewAll = `object`
        }

        quantity = findViewById(R.id.quantity)
        detailimg = findViewById(R.id.detail_img)
        addItem = findViewById(R.id.add_item)
        removeItem = findViewById(R.id.delete_item)
        addtoCart = findViewById(R.id.buttonaddtocart)
        price = findViewById(R.id.detail_price)
        rating = findViewById(R.id.detailrating)
        description = findViewById(R.id.descriptiondetail)

        addtoCart.setOnClickListener {
                addetCart()
        }


        addItem.setOnClickListener {
        if(totalquantity < 10){
            totalquantity++
            quantity.text = totalquantity.toString()
            totalPrice = viewAll!!.price!! * totalquantity
        }
        }
        removeItem.setOnClickListener {
            if(totalquantity > 1){
                totalquantity--
                quantity.text = totalquantity.toString()
                totalPrice = viewAll!!.price!! * totalquantity
            }

        }

        if(viewAll != null) {
            Glide.with(applicationContext).load(viewAll!!.img_url).into(detailimg)
            rating.text = viewAll!!.rating
            description.text = viewAll!!.description
            price.text = "Price :€" + viewAll!!.price
            totalPrice = viewAll!!.price!! * totalquantity
        }

        postTolist()

        viewpagerdetailss.adapter = ViewPageAdapter(imageList)
        viewpagerdetailss.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(viewpagerdetailss)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun addetCart() {
        val saveCurrentDate: String
        val saveCurrentTime: String
        val calForDate: Calendar = Calendar.getInstance()


        val currentDate = SimpleDateFormat("MM dd, yyyy")
        saveCurrentDate = currentDate.format(calForDate.time)

        val currentTime = SimpleDateFormat("HH:mm:ss a")
        saveCurrentTime = currentTime.format(calForDate.time)


        val cartMap: HashMap<String, Any> = HashMap()

        cartMap["productPrice"] = price.text.toString()
        cartMap["currentDate"] = saveCurrentDate
        cartMap["currentTime"] = saveCurrentTime
        cartMap["totalQuantity"] = quantity.text.toString()
        cartMap["totalPrice"] = totalPrice

        auth.currentUser?.let {
            firestore.collection("AddtoCart").document(it.uid)
                .collection("CurrentUser").add(cartMap).addOnCompleteListener {

                    Toast.makeText(applicationContext,"Added To A Cart", Toast.LENGTH_SHORT).show()
                    finish()
                }
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