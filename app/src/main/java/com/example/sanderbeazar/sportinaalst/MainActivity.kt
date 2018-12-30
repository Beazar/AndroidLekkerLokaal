package com.example.sanderbeazar.sportinaalst

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.sanderbeazar.sportinaalst.fragments.SearchFragmentFragment
import com.example.sanderbeazar.sportinaalst.fragments.SportclubLijstFragment

class MainActivity : AppCompatActivity(),  SportclubLijstFragment.SportclubCallbacks {
    override fun onMapCreated() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       // supportActionBar!!.setDisplayShowTitleEnabled(false)
        Log.d("testpurp","tijdens onCreateOptionsMenu ")
        //inflate het menu
        menuInflater.inflate(R.menu.menu_listview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, SearchFragmentFragment())
                    .addToBackStack(null)
                    .commit()

            true

        }
        R.id.action_lijst -> {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, SportclubLijstFragment())
                    .addToBackStack(null)
                    .commit()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, SportclubLijstFragment())
                .commit()


    }

}
