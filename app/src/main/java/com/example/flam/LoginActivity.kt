package com.example.flam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding:ActivityLo
    //ActionBar

    //ProgressDialog

    //FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogi
        setContentView(R.layout.activity_login)

        forgetpassword.setOnClickListener {
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        signin.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonloginandere.setOnClickListener {
            val intent = Intent(this,OtherMethodsActivity::class.java)
            startActivity(intent)
        }
    }
}