package com.example.flam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.flam.databinding.ActivityHauptBinding
import com.example.flam.databinding.ActivityMainBinding

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
                    binding.textHaupt.text = "Account"
                    binding.navbar.showBadge(R.id.nav_settings)
                }
                R.id.nav_settings -> {
                    binding.textHaupt.text = "Settings"
                    binding.navbar.dismissBadge(R.id.nav_settings)
                }
            }

        }
    }
}