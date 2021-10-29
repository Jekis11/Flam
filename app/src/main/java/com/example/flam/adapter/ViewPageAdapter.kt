package com.example.flam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flam.R
import com.example.flam.adapter.ViewPageAdapter.*

class ViewPageAdapter(private var image: List<Int>): RecyclerView.Adapter<ViewPageAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemImage: ImageView = itemView.findViewById(R.id.detail_img)

        init {
            itemImage.setOnClickListener{v: View ->
                val position : Int = adapterPosition
                Toast.makeText(itemView.context,"You clicked on Photo#${position+1}", Toast.LENGTH_SHORT).show()


            }
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
       return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_details,parent,false))
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemImage.setImageResource(image[position])

    }

    override fun getItemCount(): Int {
        return image.size
    }
}