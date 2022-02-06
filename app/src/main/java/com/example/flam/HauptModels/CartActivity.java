package com.example.flam.HauptModels;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.example.flam.R;
import com.example.flam.adapter.MyCardAdapter;
import com.example.flam.models.MyCartModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    TextView overtotalAmount;

    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    MyCardAdapter cardAdapter;
    List<MyCartModels> cartModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        overtotalAmount = findViewById(R.id.textview2);

        BroadcastReceiver mMessageReceiver = null;
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartModelsList = new ArrayList<>();
        cardAdapter = new MyCardAdapter(this, cartModelsList);
        recyclerView.setAdapter(cardAdapter);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentShapshot : task.getResult().getDocuments()) {
                        MyCartModels cartModels = documentShapshot.toObject(MyCartModels.class);
                        cartModelsList.add(cartModels);
                        cardAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int totalBill = intent.getIntExtra("totalAmount", 0);
                overtotalAmount.setText("Total Bill:" + totalBill + "€");

            }
        };


    }
}