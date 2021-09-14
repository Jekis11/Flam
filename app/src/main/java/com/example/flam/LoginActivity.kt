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
import kotlinx.android.synthetic.main.activity_login.buttonlogin


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val TAG = MainActivity::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val autr = FirebaseAuth.getInstance()



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


        buttonloginandere.setOnClickListener {
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.FacebookBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().setDefaultCountryIso("MD").build()
            )

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
                    .setTheme(R.style.SigninTheme)
                    .build(),
                RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                val intent = Intent(this, ExitLoginActivity::class.java)
                intent.putExtra(USER_ID, user!!.uid)
                startActivity(intent)
            } else {
                Log.e(TAG, "Sign-in failed", response!!.error)
            }
        }

    }

    companion object {
        const val USER_ID = "user_id"
        const val RC_SIGN_IN = 15
    }

}







