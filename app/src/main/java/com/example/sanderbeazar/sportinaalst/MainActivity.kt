package com.example.sanderbeazar.sportinaalst

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import com.example.sanderbeazar.sportinaalst.fragments.SearchFragmentFragment
import com.example.sanderbeazar.sportinaalst.fragments.SportclubDetailFragment
import com.example.sanderbeazar.sportinaalst.fragments.SportclubLijstFragment

class MainActivity : AppCompatActivity(),  SportclubLijstFragment.SportclubCallbacks {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflate het menu
        menuInflater.inflate(R.menu.menu_listview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            val sportclubDetailFragment = SportclubDetailFragment()
            if(findViewById<View>(R.id.container_detail)==null) { //Smartphone
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main, SearchFragmentFragment())
                        .addToBackStack(null)
                        .commit()
                true
            }else{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container_detail, SearchFragmentFragment())
                        .addToBackStack(null)
                        .commit()
                true
            }
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
        setContentView(R.layout.activity_masterdetail)

        var fragment = supportFragmentManager.findFragmentById(R.id.container_main)
        if (fragment == null) {
            fragment = SportclubLijstFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.container_main, fragment)
                    .commit()

            if(findViewById<View>(R.id.container_detail)!=null){ //Smartphone
                val searchFragment = SearchFragmentFragment()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container_detail, searchFragment)
                        .addToBackStack(null)
                        .commit()
        }

    }
    }
    override fun OnSportclubSelected(item: Sportclub) {
        val sportclubDetailFragment = SportclubDetailFragment()
        if(findViewById<View>(R.id.container_detail)==null){ //Smartphone
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, sportclubDetailFragment)
                    .addToBackStack(null)
                    .commit()
        }else{ //tablet
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_detail, sportclubDetailFragment)
                    .addToBackStack(null)
                    .commit()
        }
        sportclubDetailFragment.addObject(item)
    }




}
