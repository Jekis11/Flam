package com.example.flam.HauptModels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flam.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        this.googleMap = googleMap

        val andernach = LatLng(50.43109, 7.40425)
        googleMap.addMarker(MarkerOptions().position(andernach).title("Andernach"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(andernach))
    }

}
