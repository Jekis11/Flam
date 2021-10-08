package com.example.flam.HauptModels




import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flam.R
import com.example.flam.databinding.FragmentPersonBinding
import com.example.flam.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference



class PersonFragment(val packageName: String) : Fragment(R.layout.fragment_person) {

    private lateinit var binding: PersonFragment
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri : Uri
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPersonBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.buttonupdate.setOnClickListener {

            showProgressBar()
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

                         hideProgressBar()
                        Toast.makeText(requireContext(),"Failed to update profile",Toast.LENGTH_SHORT).show()

                    }
                }
            }



        }
        return binding.root
    }

    private fun uploadProfilePic() {

        val uri: Uri = Uri.parse("android.resource://$packageName/${R.drawable.cat}")

        storageReference = FirebaseStorage.getInstance().getReference("Users/"+auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {
            hideProgressBar()
            Toast.makeText(requireContext(),"Profile Succesfully updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            hideProgressBar()
            Toast.makeText(requireContext(),"Failed to upload the image",Toast.LENGTH_SHORT).show()
        }
    }



    private fun showProgressBar(){
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }

    private fun hideProgressBar(){

        dialog.dismiss()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}





















