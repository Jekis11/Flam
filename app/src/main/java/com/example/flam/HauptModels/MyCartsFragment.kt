package com.example.flam.HauptModels

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flam.R
import com.example.flam.adapter.MyCardAdapter
import com.example.flam.databinding.FragmentPersonBinding
import com.example.flam.models.MyCartModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MyCartsFragment : Fragment(R.layout.fragment_my_carts) {


    private lateinit var  db: FirebaseFirestore
    private lateinit var  auth: FirebaseAuth
    private lateinit var overTotalAmount: TextView

    private lateinit var  recyclerview: RecyclerView
    private lateinit var  myCardAdapter: MyCardAdapter
    private lateinit var  cartModelList: List<MyCartModels>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPersonBinding.inflate(layoutInflater)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        overTotalAmount = binding.root.findViewById(R.id.textview)
        recyclerview = binding.root.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(activity)


        activity?.let { LocalBroadcastManager.getInstance(it).registerReceiver(MyBroadcastReceiver(),IntentFilter("MyTotalAmount")) }


        cartModelList = ArrayList()
        myCardAdapter = MyCardAdapter(activity,cartModelList)
        recyclerview.adapter = myCardAdapter

        auth.currentUser?.let {
            db.collection("AddtoCart").document(it.uid)
                .collection("CurrentUser").get().addOnCompleteListener {task ->

                    if(task.isSuccessful()){
                           for (document in task.result){
                               val cardModel: MyCartModels =
                                   document.toObject(MyCartModels::class.java)
                               (cartModelList as ArrayList<MyCartModels>).add(cardModel)
                               myCardAdapter.notifyDataSetChanged()


                           }


                    }


                        }

                    }


        return binding.root
    }


    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent){

            val totalBill = intent.getIntExtra("totalAmount", 0)
            overTotalAmount.setText("Total Bill :"+totalBill+"€")


                }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}


