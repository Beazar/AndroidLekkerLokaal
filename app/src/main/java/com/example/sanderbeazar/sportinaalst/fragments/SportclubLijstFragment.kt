package com.example.sanderbeazar.sportinaalst.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.R.layout.sportclub_lijst_fragment
import com.example.sanderbeazar.sportinaalst.domain.SimpleItemRecyclerViewAdapter
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import kotlinx.android.synthetic.main.sportclub_lijst_fragment.*

class SportclubLijstFragment : Fragment() {

    private var sportclubs: List<Sportclub>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Listactivity", "onCreate")
        super.onCreate(savedInstanceState)

        Log.d("Listactivity", "onCreate2")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sportclub_lijst_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        sportclubs = createSportclubs()

        Log.d("Listactivity", "onStart1")
        lijst_fragment.adapter = SimpleItemRecyclerViewAdapter(this, sportclubs!!)
        Log.d("Listactivity", "onStart2")
    }


    public fun startNewActivityForDetail(item: Sportclub) {
        val intent = Intent(context, SportclubDetailFragment::class.java).apply {
            putExtra(SportclubDetailFragment.ARG_SPORTCLUB, item)
        }

        //startActivity(intent)
    }

    private fun createSportclubs(): List<Sportclub> {
        val restaurantList = mutableListOf<Sportclub>()

        val resources = activity!!.applicationContext.resources
        val namen = resources.getStringArray(R.array.namen)
        val sporten = resources.getStringArray(R.array.sporten)
        val emails = resources.getStringArray(R.array.emails)
        val websites = resources.getStringArray(R.array.websites)
        val postcodes = resources.getIntArray(R.array.postcodes)
        val adressen = resources.getStringArray(R.array.adressen)
        //val urls = resources.getStringArray(R.array.urls)

        // Get rage face images.
       // val typedArray = resources.obtainTypedArray(R.array.images)
        val imageIds = IntArray(namen.size)
        for (i in 0 until namen.size) {
            val deSportclub = Sportclub(namen[i], sporten[i], emails[i],websites[i],true,true, postcodes[i], adressen[i],"id"+i)
            restaurantList.add(deSportclub)
            Log.d("restos", "resto aangemaakt")
        }
        //typedArray.recycle()

        return restaurantList
    }

    override fun onStop() {
        super.onStop()

        lijst_fragment.adapter  = null
    }



}
