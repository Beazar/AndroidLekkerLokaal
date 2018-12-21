package com.example.sanderbeazar.sportinaalst.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import com.example.sanderbeazar.sportinaalst.MainActivity
import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.ui.SportclubViewmodel
import kotlinx.android.synthetic.main.sportclub_search_fragment.*
import android.widget.AdapterView
import com.example.sanderbeazar.sportinaalst.domain.SimpleItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.sportclub_lijst_fragment.*


class SearchFragmentFragment : Fragment{

    private lateinit var viewModel: SportclubViewmodel

    private var checkedJongen : CheckBox? = null;
    private var checkedMeisje: CheckBox? = null;
    private var sportSpinner: Spinner? = null;
    private var postcodeSpinner: Spinner? = null

    private var postcode: String = "Alle";
    private var sport: String = "Alle";
    private var meisjes: Boolean = false;
    private var jongens: Boolean = false;


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear();
        inflater?.inflate(R.menu.menu_searchview, menu);
/*
            btn_zoek.setOnClickListener {
                val intent = Intent(activity!!, MainActivity::class.java)
                startActivity(intent)  //is dit nodig?
            }*/

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

        checkedJongen = this.checkBox_jongens
        checkedMeisje = this.checkBox_meisjes
        sportSpinner = this.spinner_sport
        postcodeSpinner = this.postcodeSpinner


        spinner_postcode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                postcode = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        };






        spinner_sport.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sport = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        };

        checkBox_jongens.setOnClickListener({
            this.jongens = checkedJongen!!.isChecked()
        })

        checkBox_meisjes.setOnClickListener({
            this.meisjes = checkedMeisje!!.isChecked()
        })

            btn_zoek.setOnClickListener {
            viewModel.getSportclubs().observe(this, Observer {
                var sportclubs = it!!.filter {
                    club ->
                    (club.jongen == jongens ||
                            club.meisje == meisjes)
                }

                if(postcode != "Alle"){
                    sportclubs = sportclubs.filter {
                        club -> club.Postcode == postcode
                    }
                }
                if(sport != "Alle"){
                    sportclubs = sportclubs.filter {
                        club -> club.sport == sport
                    }
                }
                Log.d("testpurp2",sportclubs.size.toString())

                val sportclubLijstFragment = SportclubLijstFragment()
                this.fragmentManager!!.beginTransaction()
                        .replace(R.id.container_main, sportclubLijstFragment)
                        .addToBackStack(null)
                        .commit()

                var pairs = sportclubs!!.map{club -> club.naam to club}
                var sorted = pairs.sortedWith(compareBy { club -> club.first })
                sportclubLijstFragment.zoeken(sorted.map{club -> club.second})
            })

            }




    }



}