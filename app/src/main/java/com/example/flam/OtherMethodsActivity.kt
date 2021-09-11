package com.example.flam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_other_methods.*

class OtherMethodsActivity : AppCompatActivity() {
    private val TAG = OtherMethodsActivity::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_methods)

        buttonloginemail.setOnClickListener {

            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )

            startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
                RC_SIGN_IN)

        }

            val auth = FirebaseAuth.getInstance()
            if(auth.currentUser != null){
                val intent = Intent(this,ExitLoginActivity::class.java)
                intent.putExtra(USER_ID, auth.currentUser!!.uid)
                startActivity(intent)
            }


    }
    companion object {
        const val USER_ID="user_id"
        const val RC_SIGN_IN = 15
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val response = IdpResponse.fromResultIntent(data)

            if(resultCode == Activity.RESULT_OK){
                val user = FirebaseAuth.getInstance().currentUser
                val intent = Intent(this,ExitLoginActivity::class.java)
                intent.putExtra(USER_ID, user!!.uid)
                startActivity(intent)
            }
            else{
                Log.e(TAG,"Sign-in failed", response!!.error)
            }
        }
    }
}