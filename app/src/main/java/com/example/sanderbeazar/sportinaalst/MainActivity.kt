package com.example.sanderbeazar.sportinaalst

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sanderbeazar.sportinaalst.fragments.SportclubLijstFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, SportclubLijstFragment())
                .commit()
    }
}
