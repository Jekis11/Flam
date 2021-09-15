package com.example.flam

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import  android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.util.Patterns
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import bolts.Bolts
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.buttonlogin


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var CheckBox: CheckBox
    private val TAG = MainActivity::class.qualifiedName
    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
    get() =
        @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication error: $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Authentication success!")
                startActivity(Intent(this@LoginActivity,HauptActivity::class.java))
            }
        }



    private fun notifyUser(message: String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val autr = FirebaseAuth.getInstance()

        checkBiometricSupport()



        //checkbox


        buttonfinger.setOnClickListener{
            val biometricPrompt:BiometricPrompt  = BiometricPrompt.Builder(this)
                .setTitle("Title of prompt")
                .setSubtitle("Authentication is reguired")
                .setDescription("This app uses fingerprint protection to keep your data secure")
                .setNegativeButton("Cancel",this.mainExecutor, DialogInterface.OnClickListener{dialog, which ->
                    notifyUser("Authentication cancelled")
                }).build()
            biometricPrompt.authenticate(getCancellationSignal(),mainExecutor,authenticationCallback)
        }


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

    private fun getCancellationSignal() : CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport() : Boolean {
        val keyguardManager:KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if(!keyguardManager.isKeyguardSecure){
            notifyUser("Fingerprint authentication han not been enabled in settings")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)!= PackageManager.PERMISSION_GRANTED){
            notifyUser("Fingerprint authentication permission is not enabled")
            return false
        }
        return if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        }
        else true
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
                startActivity(Intent(this, HauptActivity::class.java))
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
                val intent = Intent(this, HauptActivity::class.java)
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







