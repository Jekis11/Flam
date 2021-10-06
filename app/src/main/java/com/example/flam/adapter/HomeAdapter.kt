package com.example.flam.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flam.R
import com.example.flam.models.HomeCategory
import com.example.flam.models.PopularModel

 class HomeAdapter(
    private val context: Context,
    private val categoryList: List<HomeCategory>
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(categoryList[position].img_url).into(holder.catImg)
        holder.name.text = categoryList[position].name

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var catImg: ImageView = itemView.findViewById(R.id.home_cat_img)
        var name: TextView = itemView.findViewById(R.id.cat_home_name)


    }

}