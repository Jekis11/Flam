package com.example.flam


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.example.flam.HauptModels.AccountActivity
import com.example.flam.HauptModels.ContactGoogleActivity
import com.example.flam.HauptModels.ViewPagerAdapter
import com.example.flam.databinding.ActivityHauptBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_haupt.*
import java.util.ArrayList

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
                R.id.home -> {
                    viewpager.currentItem = 0
                    binding.textHaupt.text = "Home"

                }

                R.id.nav_contact -> {
                    viewpager.currentItem = 1
                    binding.textHaupt.text = "Contact"
                    val contactintent = Intent(this, ContactGoogleActivity::class.java)
                    startActivity(contactintent)

                }
                R.id.nav_person -> {
                    viewpager.currentItem = 2
                    binding.textHaupt.text = "Account"
                    binding.navbar.showBadge(R.id.nav_settings)

                }

                R.id.nav_settings -> {
                    viewpager.currentItem = 3
                    binding.textHaupt.text = "Settings"
                    binding.navbar.showBadge(R.id.nav_settings)

                }
            }
        }

        viewpager.setOnTouchListener(View.OnTouchListener{v, event -> true})
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager).apply {
            list = ArrayList<String>().apply {
                add("Home")
                add("Contact")
                add("Account")
                add("Settings")
            }
        }
    }



}