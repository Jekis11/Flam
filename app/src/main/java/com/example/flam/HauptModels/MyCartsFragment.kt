package com.example.flam.HauptModels

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flam.R
import com.example.flam.adapter.MyCardAdapter
import com.example.flam.databinding.FragmentPersonBinding
import com.example.flam.models.MyCartModels
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentSnapshot





class MyCartsFragment : Fragment(R.layout.fragment_my_carts) {


    private lateinit var  db: FirebaseFirestore
    private lateinit var  auth: FirebaseAuth

    private lateinit var  recyclerview: RecyclerView
    private lateinit var  myCardAdapter: MyCardAdapter
    private lateinit var  cartModelList: List<MyCartModels>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPersonBinding.inflate(layoutInflater)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        recyclerview = binding.root.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(activity)


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}


