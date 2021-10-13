package com.example.flam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
    private lateinit var toolbarr : androidx.appcompat.widget.Toolbar
    private var viewAllList: List<ViewAll>? = null
    private lateinit var viewAll : RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        db = FirebaseFirestore.getInstance()
        val type = intent.getStringExtra("type")
        viewAll = findViewById(R.id.view_all_rec)
        toolbarr = findViewById(R.id.toolbar)
        setSupportActionBar(toolbarr)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        viewAll.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        viewAllList = ArrayList()
        viewAllAdapter = ViewAllAdapter(this, viewAllList as ArrayList<ViewAll>)
        viewAll.adapter = viewAllAdapter


        if(type != null && type.equals("keys")){
            db.collection("AllProducts").whereEqualTo("type","keys")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val viewr = document.toObject(ViewAll::class.java)
                        (viewAllList as ArrayList<ViewAll>).add(viewr)
                        viewAllAdapter.notifyDataSetChanged()

                    }
                }
        }

        if(type != null && type.equals("studio")){
            db.collection("AllProducts").whereEqualTo("type","studio")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val viewr = document.toObject(ViewAll::class.java)
                        (viewAllList as ArrayList<ViewAll>).add(viewr)
                        viewAllAdapter.notifyDataSetChanged()

                    }
                }

            if(type != null && type.equals("drum")){
                db.collection("AllProducts").whereEqualTo("type","drum")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val viewr = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(viewr)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
        }

            if(type != null && type.equals("traditional")){
                db.collection("AllProducts").whereEqualTo("type","traditional")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val viewr = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(viewr)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
            }
            if(type != null && type.equals("wind")){
                db.collection("AllProducts").whereEqualTo("type","wind")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val viewr = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(viewr)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
            }
            if(type != null && type.equals("guitar")){
                db.collection("AllProducts").whereEqualTo("type","guitar")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val viewr = document.toObject(ViewAll::class.java)
                            (viewAllList as ArrayList<ViewAll>).add(viewr)
                            viewAllAdapter.notifyDataSetChanged()

                        }
                    }
            }
    }
}
}