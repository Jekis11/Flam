package com.example.flam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)



        buttonsubmit.setOnClickListener {
            val email: String = emailsubmit.text.toString().trim{it <= ' ' }
            if (email.isEmpty()){
                Toast.makeText(this@ForgotPasswordActivity, "Please enter email adress", Toast.LENGTH_SHORT).show()
            } else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(this@ForgotPasswordActivity, "Email send successfuly to reset your password!", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }

        }
    }
}