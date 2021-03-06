package com.example.flam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.flam.adapter.NavCategoryDetailedAdapter;
import com.example.flam.models.HomeCategory;
import com.example.flam.models.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);


        db.collection("NavCategoryDetailed")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){

                                NavCategoryDetailedModel navCategoryDetailedmodel = document.toObject(NavCategoryDetailedModel.class);
                                list.add(navCategoryDetailedmodel);
                                adapter.notifyDataSetChanged();

                            } } else{
                            Toast.makeText(NavCategoryActivity.this, "Error," +task.getException(), Toast.LENGTH_SHORT);
                        }
                    }
                });





    }
}