package com.example.flam.HauptModels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.flam.R
import kotlinx.android.synthetic.main.fragment_oigen.view.*

private const val ARG_PARAM1 = "param1"

class ViewPagerAdapter (supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager){

    var list = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        return Oigen.newInstance(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }
    class Oigen: Fragment(){
        private var param1: String? = ""
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.getString(ARG_PARAM1)
            }
        }

        companion object{
            @JvmStatic
            fun newInstance(
                param1: String
            ) = Oigen().apply {
                arguments=Bundle().apply {
                    putString(ARG_PARAM1,param1)
                }
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var rootView = LayoutInflater.from(context).inflate(R.layout.fragment_oigen,null, false)
            return rootView
        }
    }

}