package com.example.flam.HauptModels




import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.flam.R
import com.example.flam.databinding.FragmentPersonBinding
import com.example.flam.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast.makeText as makeText1

class PersonFragment: Fragment(R.layout.fragment_person) {

    private lateinit var binding: PersonFragment
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPersonBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.buttonupdate.setOnClickListener {

            val name = binding.nameperson.text.toString()
            val phone = binding.phoneperson.text.toString()
            val email = binding.emaulperson.text.toString()
            val adress = binding.profileadress.text.toString()

            val user = User(name,phone,email,adress)
            if(uid != null){

                databaseReference.child(uid).setValue(user).addOnCompleteListener{
                    if(it.isSuccessful){

                        uploadProfilePic()

                    }else {

                        Toast.makeText(requireContext(),"Failed to update profile",Toast.LENGTH_SHORT).show()

                    }
                }
            }



        }
        return binding.root
    }

    private fun uploadProfilePic() {
       // dsds
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}





















