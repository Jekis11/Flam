package com.example.flam.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flam.DetailsActivity
import com.example.flam.R
import com.example.flam.ViewAllActivity
import com.example.flam.models.PopularModel
import com.example.flam.models.ViewAll

class ViewAllAdapter(
    private val context: Context,
    private val List: List<ViewAll>
) :
    RecyclerView.Adapter<ViewAllAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_all_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(List[position].img_url).into(holder.imageview)
        holder.name.text = List[position].name
        holder.rating.text = List[position].rating
        holder.description.text =List[position].description
        holder.price.text ="€" + List[position].price

        holder.itemView.setOnClickListener {
            val intents = Intent(context, DetailsActivity::class.java)
            intents.putExtra("detail",List[position].type)
            context.startActivity(intents)

        }
    }



    override fun getItemCount(): Int {
        return List.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageview: ImageView = itemView.findViewById(R.id.imageview)
        var name: TextView = itemView.findViewById(R.id.nav_cat_name)
        var description: TextView = itemView.findViewById(R.id.nav_cat_des)
        var rating: TextView = itemView.findViewById(R.id.rec_rating)
        var price: TextView = itemView.findViewById(R.id.pricenaveuro)

    }
}