package com.example.flam


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.flam.HauptModels.AccountActivity
import com.example.flam.HauptModels.ContactGoogleActivity
import com.example.flam.databinding.ActivityHauptBinding
import com.example.flam.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_haupt.*

class HauptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHauptBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHauptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpTabbar()
    }


    private fun setUpTabbar() {
        binding.navbar.setOnItemSelectedListener {
            when(it) {
                R.id.home -> binding.textHaupt.text = "Home"
                R.id.nav_contact -> binding.textHaupt.text = "Contact"
                R.id.nav_person -> {
                    val intent = Intent(this,AccountActivity::class.java)
                    startActivity(intent)
                    binding.textHaupt.text = "Account"
                    binding.navbar.showBadge(R.id.nav_person)
                }
                R.id.nav_settings -> {
                    val intent = Intent(this,AccountActivity::class.java)
                    startActivity(intent)
                    binding.textHaupt.text = "Settings"
                    binding.navbar.dismissBadge(R.id.nav_settings)
                }
            }

        }
    }



}