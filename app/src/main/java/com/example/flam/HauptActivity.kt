package com.example.flam


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.flam.HauptModels.ContactFragment
import com.example.flam.HauptModels.PersonFragment
import com.example.flam.HauptModels.SettingsFragment
import com.example.flam.HauptModels.ViewPagerAdapter
import com.example.flam.databinding.ActivityHauptBinding

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
                    binding.navbar.showBadge(R.id.home)

                }

                R.id.nav_contact -> {
                    val contactFragment = ContactFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, contactFragment).commit()
                    viewpager.currentItem = 1

                }
                R.id.nav_person -> {
                    val personFragment = PersonFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, personFragment).commit()
                    viewpager.currentItem = 2


                }

                R.id.nav_settings -> {
                    val settingsFragment = SettingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, settingsFragment).commit()
                    viewpager.currentItem = 3
                    binding.navbar.showBadge(R.id.nav_settings,10000)

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