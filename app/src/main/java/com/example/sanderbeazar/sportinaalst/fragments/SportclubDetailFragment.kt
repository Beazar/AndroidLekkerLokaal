package com.example.sanderbeazar.sportinaalst.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import kotlinx.android.synthetic.main.sportclub_detail_fragment.*
import kotlinx.android.synthetic.main.sportclub_detail_fragment.view.*
import kotlinx.android.synthetic.main.sportclub_lijst_content.*


class SportclubDetailFragment : Fragment() {

    var root: View? = null
    var sportclub : Sportclub? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root =  inflater.inflate(R.layout.sportclub_detail_fragment, container, false)
        return root


    }
    override fun onResume() {
        super.onResume()

    }

    fun addObject(sportclub:Sportclub){
        this.sportclub = sportclub;
        var naamSportclubString = sportclub.naam

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_naamSportclub.text = sportclub!!.naam
        tv_adres.text = sportclub!!.adres + " " + sportclub!!.Postcode
        tv_afstand.text = "500 m"
        tv_email.text = sportclub!!.email
        tv_sport.text = sportclub!!.sport
        tv_website.text = sportclub!!.website
    }


    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_SPORTCLUB = "item_id"

        fun newInstance(club: Sportclub): SportclubDetailFragment {
            val args = Bundle()
            args.putSerializable(ARG_SPORTCLUB, club)
            val fragment = SportclubDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }

}
