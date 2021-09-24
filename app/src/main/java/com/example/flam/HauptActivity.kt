package com.example.flam


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.flam.HauptModels.ContactFragment
import com.example.flam.HauptModels.PersonFragment
import com.example.flam.HauptModels.SettingsFragment
import com.example.flam.HauptModels.ViewPagerAdapter
import com.example.flam.databinding.ActivityHauptBinding
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_haupt.*
import java.util.ArrayList

class HauptActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHauptBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHauptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()



        //setting toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpTabbar()
    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.themesnight -> {

            // User chose the "night themes" item
            Toast.makeText(this,"Enabled Themes Night",Toast.LENGTH_LONG).show()
            true
        }

        R.id.contactus -> {
            val intent = Intent(this,ContactFragment::class.java)
            startActivity(intent)
            // User chose the "night themes" item
            Toast.makeText(this,"Enabled Themes Night",Toast.LENGTH_LONG).show()
            true
        }

        R.id.privacypolicy -> {
            val url = Uri.parse("https://docs.google.com/document/d/1aEftqMz_lqwFD9ku2csCqc4CU_yDTAPg-NII3ajfnGM/edit")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
            true
        }

        R.id.action_logout -> {
            // User chose the "Bye" item
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            Toast.makeText(this,"Bis bald, danke!",Toast.LENGTH_LONG).show()
            finish()
            true
        }

        R.id.action_cart -> {
            // User chose the "Сart" item
            // тут просто интент поменять на переход в корзину
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            Toast.makeText(this,"Bis bald, danke!",Toast.LENGTH_LONG).show()
            finish()
            true
        }


        android.R.id.home ->{
            Toast.makeText(this,"Home action",Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }



    private fun setUpTabbar() {
        binding.navbar.setOnItemSelectedListener {
            when (it) {
                R.id.home -> {

                    viewpager.currentItem = 0
                    binding.navbar.showBadge(R.id.home)

                }

                R.id.nav_contact -> {
                    val contactFragment = ContactFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.framelayout, contactFragment).commit()
                    viewpager.currentItem = 1

                }
                R.id.nav_person -> {
                    val navpersont = PersonFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.framelayout, navpersont).commit()
                    viewpager.currentItem = 2


                }

                R.id.nav_settings -> {
                    val settingsFragment = SettingsFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.framelayout, settingsFragment).commit()
                    viewpager.currentItem = 3
                    binding.navbar.showBadge(R.id.nav_settings, 10000)

                }
            }
        }

        viewpager.setOnTouchListener(View.OnTouchListener { v, event -> true })
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

