package com.example.flam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.flam.adapter.ViewAllAdapter
import com.example.flam.models.ViewAll
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

        val type = intent.getStringExtra("type")
    }
}