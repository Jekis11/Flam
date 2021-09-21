package com.example.flam.HauptModels


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentActivity
import com.example.flam.R


class ContactFragment : FragmentActivity(R.layout.fragment_contact) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_contact)

        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
                "STORY HOURS", "LESSONS HOURS", "EVENTS AT HIS LOCATION"
        )

        // access the listView from xml file
        var mListView = findViewById<ListView>(R.id.userlist)
        arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, users)
        mListView.adapter = arrayAdapter
    }

}






