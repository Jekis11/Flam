package com.example.flam.adapter

import androidx.appcompat.app.AppCompatActivity


class ConvertActivity : AppCompatActivity() {

    PopularModel popularModel = document.toObject(PopularModel.class);
    popularModelList.add(popularModel);
    popularAdapers.notifyDataSetChanged();


    }
