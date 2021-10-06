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
import com.example.flam.models.PopularModel
import com.example.flam.models.RecommendedModel

class RecommendedAdapter(
    private val context: Context,
    private val list: List<RecommendedModel>
) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recommended_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].img_url).into(holder.recImg)
        holder.name.text = list[position].name
        holder.description.text = list[position].description
        holder.rating.text = list[position].rating


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recImg: ImageView = itemView.findViewById(R.id.rec_img)
        var name: TextView = itemView.findViewById(R.id.rec_name)
        var description: TextView = itemView.findViewById(R.id.rec_desc)
        var rating: TextView = itemView.findViewById(R.id.rec_rating)


    }
}