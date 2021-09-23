package com.example.flam.HauptModels


import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.flam.HauptActivity
import com.example.flam.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {
    val varibale = arrayOf("STORY HOURS", "LESSONS HOURS", "EVENT AT THIS LOCATION")



class ContactActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_contact)

        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel: +49263242093"))
            startActivity(intent)
        }


        textdirection.setOnClickListener { val url = Uri.parse("https://www.instagram.com/oigen_11/?hl=ru")
            val intent = Intent(Intent.ACTION_VIEW,url)
            startActivity(intent) }

        emailstore.setOnClickListener {
            val url = Uri.parse("https://g.zeos.in/?q=%D0%9A%20%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%D1%83%20%D1%82%D1%83%D1%82%20%D0%BE%D1%82%D0%BA%D1%80%D1%8B%D0%B2%D0%B0%D0%B5%D1%82%D1%81%D1%8F%20%D1%81%D0%B0%D0%B9%D1%82%20Musical%20Instrument%20Shop%20Flam")
            val intent = Intent(Intent.ACTION_VIEW,url)
            startActivity(intent)
        }

        ridewithuber.setOnClickListener {
            val url = Uri.parse("https://taxi.yandex.md/ru_md/")
            val intent = Intent(Intent.ACTION_VIEW,url)
            startActivity(intent)
        }



    }


}

    }









