package com.example.flam

import android.app.Activity
import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        buttonlogin.setOnClickListener {
            doLogin()
        }

        forgetpassword.setOnClickListener {
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }


        signup.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doLogin() {
        if (emaillogin.text.toString().isEmpty()) {
            emaillogin.error = "Please enter email"
            emaillogin.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emaillogin.text.toString()).matches()) {
            emaillogin.error = "Please enter valid email"
            emaillogin.requestFocus()
            return
        }

        if (passwordlogin.text.toString().isEmpty()) {
            passwordlogin.error = "Please enter password"
            passwordlogin.requestFocus()
            return
        }
            auth.signInWithEmailAndPassword(emaillogin.text.toString(), passwordlogin.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {

                        updateUI(null)
                    }
                }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, ExitLoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}






