package com.example.flam


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.flam.HauptModels.*
import com.example.flam.databinding.ActivityHauptBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_haupt.*
import java.util.ArrayList

class HauptActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var binding: ActivityHauptBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHauptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()
        setSupportActionBar(findViewById(R.id.toolbar))
        val drawer : DrawerLayout =findViewById(R.id.drawerlayout)
        val navView :  NavigationView = findViewById(R.id.navigationbar)
        val drawerToggle = ActionBarDrawerToggle(this, drawerlayout,toolbar, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        drawerToggle.isDrawerIndicatorEnabled = true

        // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked

        //Navigation Fragments
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_homes ->
                {
                    Toast.makeText(this,"Home",Toast.LENGTH_LONG).show()
                }

          }
            true
        }
        //setting toolbar
        //home navigation

        setUpTabbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerlayout.openDrawer(navigationbar)
        return true
    }

    override fun onBackPressed() {
        if (this.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    //Options Menu in action bar
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.themesnight -> {

            // User chose the "night themes" item
            Toast.makeText(this,"Enabled Themes Night",Toast.LENGTH_LONG).show()
            true
        }

        R.id.contactus -> {
            val contactFragment = ContactFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.framelayout, contactFragment).commit()
            Toast.makeText(this,"Enabled Themes Night",Toast.LENGTH_LONG).show()
            true
        }

        R.id.privacypolicy -> {
            val url = Uri.parse("https://docs.google.com/document/d/1aEftqMz_lqwFD9ku2csCqc4CU_yDTAPg-NII3ajfnGM/edit")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
            true
        }

            R.id.relievetension -> {
                val intent = Intent(this,RelieveTensionActivity::class.java)
                startActivity(intent)
            true
        }

        R.id.reportabug -> {
            val emailintent = Intent(Intent.ACTION_SENDTO,
            Uri.fromParts("mailto","kogyxar94@gmail.com",null))
            startActivity(Intent.createChooser(emailintent,"Send Report Bug Email..."))
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


        R.id.home ->{
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

