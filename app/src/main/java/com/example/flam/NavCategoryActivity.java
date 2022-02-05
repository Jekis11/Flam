package com.example.flam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.flam.adapter.NavCategoryDetailedAdapter;
import com.example.flam.models.NavCategoryDetailedModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    NavCategoryDetailedAdapter adapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);


        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.nav_cat_det_rec);
        list = new ArrayList<>();
        adapter = new NavCategoryDetailedAdapter(this,list);
    }
}