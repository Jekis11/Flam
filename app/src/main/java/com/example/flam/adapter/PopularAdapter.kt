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

class PopularAdapters(
    private val context: Context,
    private val popularModelList: List<PopularModel>
) :
    RecyclerView.Adapter<PopularAdapters.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(popularModelList[position].img_url).into(holder.popimg)
        holder.name.text = popularModelList[position].name
        holder.rating.text = popularModelList[position].rating
        holder.description.text = popularModelList[position].description
        holder.discount.text = popularModelList[position].discount
    }

    override fun getItemCount(): Int {
        return popularModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var popimg: ImageView = itemView.findViewById(R.id.pop_img)
        var name: TextView = itemView.findViewById(R.id.nameproducts)
        var description: TextView = itemView.findViewById(R.id.pop_desc)
        var rating: TextView = itemView.findViewById(R.id.rating)
        var discount: TextView = itemView.findViewById(R.id.pop_discount)

    }
}