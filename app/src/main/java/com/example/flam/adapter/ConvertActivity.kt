package com.example.flam.adapter

import androidx.appcompat.app.AppCompatActivity


class ConvertActivity : AppCompatActivity() {

    }


db.collection("")
.get()
.addOnCompeleteListener(new OnCompleteListener<QuerySnapshot>(){

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if(task.isSuccessful()){
            for (QueryDocumentSnapshot document: task.getResult()){

                PopularModel popularModel = document.toObject(PopularModel.class);
                popularModelList.add(popularModel);
                pupularAdapters.notifyDataSetChanged();


            }}

    }
