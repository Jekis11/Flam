package com.example.flam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flam.R;
import com.example.flam.models.MyCartModels;

import java.util.List;

public  class MyCardAdapter  extends RecyclerView.Adapter<MyCardAdapter.ViewHolder> {

    Context context;
    List<MyCartModels> cartModelsList;

    public MyCardAdapter(Context context, List<MyCartModels> cartModelsList) {
        this.context = context;
        this.cartModelsList = cartModelsList;
    }

    @NonNull
    @Override
    public MyCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyCardAdapter.ViewHolder holder, int position) {
        holder.name.setText(cartModelsList.get(position).getProductName());
        holder.price.setText(cartModelsList.get(position).getProductPrice());
        holder.data.setText(cartModelsList.get(position).getCurrentDate());
        holder.quantity.setText(cartModelsList.get(position).getTotalQuantity());
        holder.totalPrice.setText(cartModelsList.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return cartModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, data, time, quantity, totalPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.productname);
            price = itemView.findViewById(R.id.productprice);
            data = itemView.findViewById(R.id.currentdate);
            time = itemView.findViewById(R.id.currenttime);
            quantity = itemView.findViewById(R.id.total_Quantity);
            totalPrice = itemView.findViewById(R.id.totalPrice);

        }
    }
}

