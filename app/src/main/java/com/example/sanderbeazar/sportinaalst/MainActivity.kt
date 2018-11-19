package com.example.sanderbeazar.sportinaalst

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sanderbeazar.sportinaalst.fragments.SportclubLijstFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity(),  SportclubLijstFragment.SportclubCallbacks {
    override fun onMapCreated() {
        Log.d("testpurp","tijdens 1 onMapCreated ")
        Log.d("testpurp", supportFragmentManager.findFragmentById(R.id.map).toString())
        /*mapFragment =  supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
        })*/
        Log.d("testpurp","tijdens 2 onMapCreated ")
    }


    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, SportclubLijstFragment())
                .commit()


    }

}
