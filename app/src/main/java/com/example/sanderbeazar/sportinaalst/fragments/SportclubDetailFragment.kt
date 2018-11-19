package com.example.sanderbeazar.sportinaalst.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.*
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
        btn_route.setOnClickListener{
            val uri = sportclub!!.adres.trim() +",+"+ sportclub!!.Postcode.trim() + "+Belgium"
            val gmmIntentUri = Uri.parse("google.navigation:q="+uri);
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }

        btn_mail.setOnClickListener{
            /*
            /* Create the Intent */
            var emailIntent = Intent(android.content.Intent.ACTION_SEND);

/* Fill it with Data */
            emailIntent.setType("plain/text");
            Log.d("testpurp",this.sportclub!!.email)
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, this.sportclub!!.email);
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "E-mail via SportInAalst");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");

/* Send it off to the Activity-Chooser */
            context!!.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            */
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", sportclub!!.email, null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact via SportInAalstApp")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }

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
