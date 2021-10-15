package com.example.flam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bolts.Task
import com.example.flam.adapter.PopularAdapter
import com.example.flam.adapter.ViewAllAdapter
import com.example.flam.models.PopularModel
import com.example.flam.models.ViewAll
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewAllActivity : AppCompatActivity() {

    private lateinit var viewAllAdapter: ViewAllAdapter
    private var db = Firebase.firestore
    private lateinit var toolbar : androidx.appcompat.widget.Toolbar
    private var viewAllList: List<ViewAll>? = null
    private lateinit var recyclerView : RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        db = FirebaseFirestore.getInstance()
        val type = intent.getStringExtra("type")

        recyclerView = findViewById(R.id.view_all_rec)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }



        recyclerView.layoutManager = LinearLayoutManager(this)
        viewAllList = ArrayList()
        viewAllAdapter = ViewAllAdapter(this, viewAllList as ArrayList<ViewAll>)
        recyclerView.adapter = viewAllAdapter


        if(type != null && type == "keys"){
            db.collection("AllProducts").whereEqualTo("type","keys")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val keys = document.toObject(ViewAll::class.java)
                        (viewAllList as ArrayList<ViewAll>).add(keys)
                        viewAllAdapter.notifyDataSetChanged()

                    }
                }
        }

        if(type != null && type == "studio"){
            db.collection("AllProducts").whereEqualTo("type","studio")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val studio = document.toObject(ViewAll::class.java)
                        (viewAllList as ArrayList<ViewAll>).add(studio)
                        viewAllAdapter.notifyDataSetChanged()

                    }

                }
            }

            if(type != null && type == "drum"){
                db.collection("AllProducts").whereEqualTo("type","drum")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val drum = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(drum)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
                }

            if(type != null && type == "traditional"){
                db.collection("AllProducts").whereEqualTo("type","traditional")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val trad = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(trad)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
                 }

                 if(type != null && type == "wind"){
                db.collection("AllProducts").whereEqualTo("type","wind")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val wind = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(wind)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
                  }
         if(type != null && type == "guitar"){
                db.collection("AllProducts").whereEqualTo("type","guitar")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val guitar = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(guitar)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
                 }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

