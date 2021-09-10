package com.example.flam

import android.app.ProgressDialog
import android.content.Intent
import android.media.tv.TvContract
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.text.TextUtils
import android.util.Patterns
import androidx.appcompat.app.ActionBar
import com.example.flam.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding:ActivityLoginBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure actionbar
        actionBar = supportActionBar!!
        actionBar.title="Login"

        //configure progressDialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In.. ")
        progressDialog.setCanceledOnTouchOutside(false)

        //configure FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, open register activity
        binding.signin.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        //handle click, begin login
        //before loggin in, validate data
        validateData()
        binding.buttonlogin.setOnClickListener {
            val intent = Intent(this, ExitLoginActivity::class.java)
            startActivity(intent)
        }

        forgetpassword.setOnClickListener {
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }


        buttonloginandere.setOnClickListener {
            val intent = Intent(this,OtherMethodsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateData() {
        //get Data
        email = binding.emaillogin.text.toString().trim()
        password = binding.passwordlogin.text.toString().trim()
        //validateData
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //invalid email format
                binding.emaillogin.error = "Invalid email format"
         }
        else if (TextUtils.isEmpty(password)){
        //no password entered
        binding.passwordlogin.error = "Please enter password"
          }
         else{
        //data is validated, begin login
        firebaseLogin()
        }
        }

    private fun firebaseLogin() {
        //show Progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {

            }
    }


    private fun checkUser() {
        //if user is a already logged in go to exit activity
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null) {
            startActivity(Intent(this, ExitLoginActivity::class.java))
            finish()
        }
        }
    }
