package com.example.sanderbeazar.sportinaalst.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.SportclubLijst.SimpleItemRecyclerViewAdapter
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import com.example.sanderbeazar.sportinaalst.ui.SportclubViewmodel
import kotlinx.android.synthetic.main.sportclub_lijst_fragment.*

class SportclubLijstFragment : Fragment() {

    private lateinit var viewModel: SportclubViewmodel
    private var sportclubs: List<Sportclub>? = null

    private var msportclubCallbacks: SportclubCallbacks? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        msportclubCallbacks = activity as SportclubCallbacks

    }

    fun filterenVanSportclubs(item: List<Sportclub>){
        this.sportclubs = item
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(activity!!).get(SportclubViewmodel::class.java)
        return inflater.inflate(R.layout.sportclub_lijst_fragment, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        if(sportclubs==null){
            viewModel.getSportclubs().observe(this, android.arch.lifecycle.Observer {
                val pairs = it!!.map{ club -> club.naam to club}
                val sorted = pairs.sortedWith(compareBy { club -> club.first })
                lijst_fragment.adapter = SimpleItemRecyclerViewAdapter(this, sorted.map { club -> club.second })
            })

        } else{
            if(sportclubs!!.isNotEmpty()){
                lijst_fragment.adapter = SimpleItemRecyclerViewAdapter(this, sportclubs!!)
            }else{
                lijst_fragment.visibility = View.GONE
            }
        }

        }


    fun startNewActivityForDetail(item: Sportclub) {

        msportclubCallbacks!!.onSportclubSelected(item)
    }

    override fun onStop() {
        super.onStop()

        lijst_fragment.adapter  = null
    }

    interface SportclubCallbacks {
        fun onSportclubSelected(item: Sportclub)
    }

}


