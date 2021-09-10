package com.example.flam

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.flam.databinding.ActivityLoginBinding
import com.example.flam.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.signin

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        signin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        buttonsignup.setOnClickListener {
            signUpUser()
        }

    }
    private fun signUpUser() {

        if (nameregister.text.toString().isEmpty()) {
            nameregister.error = "Please enter name"
            nameregister.requestFocus()
            return
        }

        if (emailregister.text.toString().isEmpty()) {
            emailregister.error = "Please enter email"
            emailregister.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailregister.text.toString()).matches()) {
            emailregister.error = "Please enter valid email"
            emailregister.requestFocus()
            return
        }

        if (passwordregister.text.toString().isEmpty()) {
            passwordregister.error = "Please enter password"
            passwordregister.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(emailregister.text.toString(), passwordregister.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}