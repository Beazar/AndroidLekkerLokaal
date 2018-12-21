package com.example.sanderbeazar.sportinaalst.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.ui.SportclubViewmodel
import kotlinx.android.synthetic.main.sportclub_search_fragment.*

class SearchFragmentFragment : Fragment{

    private lateinit var viewModel: SportclubViewmodel

    private var checkedJongen : Int? = null;
    private var checkedMeisje: Int? = null;
    private var sportSpinner: Int? = null;
    private var postcodeSpinner: Int? = null


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear();
        inflater?.inflate(R.menu.menu_searchview, menu);

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(SportclubViewmodel::class.java)
        return inflater.inflate(R.layout.sportclub_search_fragment,container,false)
    }

    constructor(){
        setHasOptionsMenu(true);
    }

    override fun onStart() {
        super.onStart()
        val sportSpinnerArray = arrayOf("Alle","Voetbal","Basketbal","Floorball","Dans","Volleybal","Tennis","Andere")
        val s = activity!!.findViewById<Spinner>(R.id.spinner_sport)
        val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item,sportSpinnerArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        s.adapter = adapter

        val postcodeSpinnerArray = arrayOf("Alle","9300 Aalst","9308 Hofstade","9308 Gijzegem","9310 Moorsel",
                "9310 Herdersem","9310 Baardegem","9310 Meldert","9320 Erembodegem","9320 Nieuwerkerken")
        val p = activity!!.findViewById<Spinner>(R.id.spinner_postcode)
        val adapter2 = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item,postcodeSpinnerArray)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        p.adapter = adapter2


    }



}