package com.example.flam

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.flam.databinding.ActivityLoginBinding
import com.example.flam.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityRegisterBinding


    //ActionBar
    private lateinit var actionBar: ActionBar

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var name = ""
    private var email = ""
    private var password = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure ActionBar//enable back button
        actionBar = supportActionBar!!
        actionBar.title = "Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)


        //configure ProgressDialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account.. ")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //handle click,begin signup
        binding.buttonsignup.setOnClickListener {
            //validate
            validateData()
        }


        signin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateData() {
        //get data
        name = binding.nameregister.text.toString().trim()
        email = binding.emailregister.text.toString().trim()
        password = binding.passwordregister.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invdalid email format
            binding.emailregister.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(name)){
            //name inst entered
            binding.nameregister.error = "Please enter Name"
        }
        else if(TextUtils.isEmpty(password)){
            //password isnt entered
            binding.passwordregister.error = "Please enter Password"
        }
        else if(password.length <6){
            //password length is less than 6
            binding.passwordregister.error = "Password must atleast 6 charachters long"
        }
        else {
            //data is valid, continue sign up
            firebaseSignUp()
        }

    }

    private fun firebaseSignUp() {
        //show Progress

        progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //signup gluck
                progressDialog.dismiss()
                //get current user
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Account Created with email $email",Toast.LENGTH_SHORT).show()


                //open exit activity
                startActivity(Intent(this,ExitLoginActivity::class.java))
                finish()

            }
            .addOnFailureListener{e->
                //signup nicht gluck
                progressDialog.dismiss()
                Toast.makeText(this,"SignUp failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //назад к прошлому активити
        return super.onSupportNavigateUp()
    }
}