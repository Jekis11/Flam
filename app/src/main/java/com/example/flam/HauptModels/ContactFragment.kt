package com.example.flam.HauptModels



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import com.example.flam.HauptActivity
import com.example.flam.LoginActivity
import com.example.flam.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {


    private lateinit var listView: ListView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_contact, container, false)
        listView = rootView.findViewById(R.id.userlist)
        val testList = arrayOf("STORY HOURS", "LESSONS HOURS", "EVENTS AT THIS LOCATION")
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, testList)
        listView.adapter = adapter
        return rootView


    }

    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.lay_custom_dialogeins, null)
        val customDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .show()
        val btDismiss = dialogView.findViewById<Button>(R.id.btDismissCustomDialog)
        btDismiss.setOnClickListener {
            customDialog.dismiss()
        }
    }

    private fun showCustomDialogZwei() {
        val dialogView = layoutInflater.inflate(R.layout.lay_customdialogzwei, null)
        val customDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .show()
        val btDismiss = dialogView.findViewById<Button>(R.id.btDismissCustomDialog)
        btDismiss.setOnClickListener {
            customDialog.dismiss()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            if (position==0){
                showCustomDialog()
            }
            if (position==1){
                showCustomDialogZwei()
            }
            if (position==2){
                showCustomDialog()
            }

        }


        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel: +49263242093"))
            startActivity(intent)
        }


        textdirection.setOnClickListener {
            val url = Uri.parse("https://www.instagram.com/oigen_11/?hl=ru")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }

        emailstore.setOnClickListener {
            val url =
                Uri.parse("https://g.zeos.in/?q=%D0%9A%20%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%D1%83%20%D1%82%D1%83%D1%82%20%D0%BE%D1%82%D0%BA%D1%80%D1%8B%D0%B2%D0%B0%D0%B5%D1%82%D1%81%D1%8F%20%D1%81%D0%B0%D0%B9%D1%82%20Musical%20Instrument%20Shop%20Flam")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }

        ridewithuber.setOnClickListener {
            val url = Uri.parse("https://taxi.yandex.md/ru_md/")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }



    }
 }














