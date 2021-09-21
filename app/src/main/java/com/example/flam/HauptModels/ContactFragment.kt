package com.example.flam.HauptModels


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.flam.R

class ContactFragment : Fragment() {
    val varibale = arrayOf("STORY HOURS", "LESSONS HOURS", "EVENT AT THIS LOCATION")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_contact,container,false)
        val arrayAdapter: ArrayAdapter<*>
        var item_list = ArrayList<String>()


        val mListView = view.findViewById<ListView>(R.id.list_item)

        arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, varibale)
        mListView.adapter = arrayAdapter

        return view
    }

    }









