package com.example.flam


import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flam.HauptModels.*
import com.example.flam.adapter.HomeAdapter
import com.example.flam.adapter.PopularAdapter
import com.example.flam.adapter.RecommendedAdapter
import com.example.flam.databinding.ActivityHauptBinding
import com.example.flam.models.HomeCategory
import com.example.flam.models.PopularModel
import com.example.flam.models.RecommendedModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_haupt.*
import kotlin.collections.ArrayList
import kotlin.collections.List
import java.util.ArrayList as ArrayList1


class HauptActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //popular items
    private lateinit var popularAdapters : PopularAdapter
    private var db = Firebase.firestore
    private var popularModelList: List<PopularModel>? = null
    private lateinit var popularRec : RecyclerView

    //home category
    private lateinit var homeCatRec: RecyclerView
    private var categoryList: List<HomeCategory>? = null
    private lateinit var homeAdapter: HomeAdapter

    //Recommended items
    private lateinit var recomCatRec: RecyclerView
    private var recommededModelList: List<RecommendedModel>? = null
    private lateinit var recomededAdapter: RecommendedAdapter

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var binding: ActivityHauptBinding
    private lateinit var user: FirebaseAuth
    private lateinit var scrollView: ScrollView
    private lateinit var progresssBar: ProgressBar


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHauptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        setSupportActionBar(findViewById(R.id.toolbar))

        val drawer : DrawerLayout =findViewById(R.id.drawerlayout)
        val navView :  NavigationView = findViewById(R.id.navigationbar)
        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerlayout,
            toolbar,
            R.string.open,
            R.string.close) {}
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
        //setting toolbar
        //home navigation
        //Popular  item and Home Category
        popularRec = findViewById(R.id.pop_rec)
        homeCatRec = findViewById(R.id.explore_rec)
        recomCatRec = findViewById(R.id.recomended_rec)
        scrollView  = findViewById(R.id.scrollview)
        progresssBar = findViewById(R.id.progress_bar)

        progresssBar.visibility = View.VISIBLE
        scrollView.visibility = View.GONE



        popularRec.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        popularModelList = ArrayList()
        popularAdapters = PopularAdapter(this, popularModelList as ArrayList<PopularModel>)
        popularRec.adapter = popularAdapters

        db.collection("PopularProducts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val popularModel = document.toObject(PopularModel::class.java)
                    (popularModelList as ArrayList<PopularModel>).add(popularModel)
                    popularAdapters.notifyDataSetChanged()

                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
               Toast.makeText(this, "Error $exception",Toast.LENGTH_SHORT).show()
            }

        homeCatRec.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        categoryList = ArrayList()
        homeAdapter = HomeAdapter(this, categoryList as ArrayList<HomeCategory>)
        homeCatRec.adapter = homeAdapter

        db.collection("HomeCategory")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val home = document.toObject(HomeCategory::class.java)
                    (categoryList as ArrayList<HomeCategory>).add(home)
                    homeAdapter.notifyDataSetChanged()

                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error $exception",Toast.LENGTH_SHORT).show()
            }

        recomCatRec.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recommededModelList = ArrayList()
        recomededAdapter = RecommendedAdapter(this,  recommededModelList as ArrayList<RecommendedModel>)
        recomCatRec.adapter =  recomededAdapter

        db.collection("Recommended")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val recModel = document.toObject(RecommendedModel::class.java)
                    ( recommededModelList as ArrayList<RecommendedModel>).add(recModel)
                    recomededAdapter.notifyDataSetChanged()

                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error $exception",Toast.LENGTH_SHORT).show()
            }


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
                    val intent = Intent(this,HauptActivity::class.java)
                    startActivity(intent)
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
            list = ArrayList1<String>().apply {
                add("Home")
                add("Contact")
                add("Account")
                add("Settings")
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerlayout.closeDrawer(GravityCompat.START)
        if(item.itemId == R.id.nav_homes){
            Toast.makeText(applicationContext,"HOME SCHEISSE", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}

