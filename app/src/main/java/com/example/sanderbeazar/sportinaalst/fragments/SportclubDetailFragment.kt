package com.example.sanderbeazar.sportinaalst.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.R.drawable.*
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import com.example.sanderbeazar.sportinaalst.ui.SportclubViewmodel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.sportclub_detail_fragment.*
import kotlinx.android.synthetic.main.sportclub_detail_fragment.view.*
import kotlinx.android.synthetic.main.sportclub_lijst_content.*
import kotlin.math.floor


class SportclubDetailFragment : Fragment(), OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap?) {

        viewModel.getSportclubs().observe(this, Observer{
            mMap = googleMap!!
            val coder = Geocoder(activity!!)
            var address : List<Address>
            address = coder.getFromLocationName(sportclub!!.adres + sportclub!!.Postcode, 1)
            val locatie = address[0]
            mMap.addMarker(MarkerOptions().position(LatLng(locatie.latitude, locatie.longitude)).title(sportclub!!.naam).snippet(sportclub!!.adres))
            val location = LatLng(locatie.latitude, locatie.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17.5f))

        })
    }

    var root: View? = null
    var sportclub : Sportclub? = null
    private lateinit var viewModel: SportclubViewmodel
    private lateinit var mMap : GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(SportclubViewmodel::class.java)
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
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", sportclub!!.email, null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact via SportInAalstApp")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

    }

    fun addObject(sportclub:Sportclub){
        this.sportclub = sportclub
        if(sportclub.email == null ||sportclub.email == ""){
            this.btn_mail.setVisibility(View.INVISIBLE)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_naamSportclub.text = sportclub!!.naam
        tv_adres.text = sportclub!!.adres + " " + sportclub!!.Postcode
        tv_afstand.text = "500 m"
        tv_sport.text = sportclub!!.sport
        tv_website.text = sportclub!!.website
        if(sportclub!!.sport.toLowerCase() == "andere"){
            Picasso.get().load(andere).fit().into(imageView)
        } else if(sportclub!!.sport.toLowerCase() == "voetbal"){
            Picasso.get().load(voetbal).fit().into(imageView)
        } else if(sportclub!!.sport.toLowerCase() == "basketbal"){
            Picasso.get().load(basketbal).fit().into(imageView)
        } else if(sportclub!!.sport.toLowerCase() == "dans"){
            Picasso.get().load(dans).fit().into(imageView)
        } else if(sportclub!!.sport.toLowerCase() == "floorball"){
            Picasso.get().load(floorball).fit().into(imageView)
        } else if(sportclub!!.sport.toLowerCase() == "volleybal"){
            Picasso.get().load(volleybal).fit().into(imageView)}
     else if(sportclub!!.sport.toLowerCase() == "tennis"){
        Picasso.get().load(tennis).fit().into(imageView)}
    }


    override fun onStart() {
        super.onStart()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this);
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
