package com.example.flam

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flam.adapter.PopularAdapter
import com.example.flam.adapter.ViewAllAdapter
import com.example.flam.models.PopularModel
import com.example.flam.models.ViewAll
import com.facebook.internal.WebDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewAllActivity : AppCompatActivity() {

    private lateinit var viewAllAdapter: ViewAllAdapter
    private var db = Firebase.firestore
    private var viewAllList: List<ViewAll>? = null
    private lateinit var viewAll : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        db = FirebaseFirestore.getInstance()
        val type = intent.getStringExtra("type")
        viewAll = findViewById(R.id.view_all_rec)

        viewAll.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        viewAllList = ArrayList()
        viewAllAdapter = ViewAllAdapter(this, viewAllList as ArrayList<ViewAll>)
        viewAll.adapter = viewAllAdapter


        if(type != null && type.equals("keys")){

          db.collection("AllProducts").whereEqualTo("type","keys").get()
              .addOnCompleteListener {  }
        }

    }
}