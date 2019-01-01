package com.example.sanderbeazar.sportinaalst.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import com.example.sanderbeazar.sportinaalst.ui.SportclubViewmodel
import kotlinx.android.synthetic.main.sportclub_search_fragment.*


class SearchFragmentFragment : Fragment() {

    private lateinit var viewModel: SportclubViewmodel

    private var checkedJongen : RadioButton? = null
    private var checkedMeisje: RadioButton? = null
    private var sportSpinner: Spinner? = null

    private var postcode: String = "Alle"
    private var sport: String = "Alle"
    private var meisjes: Boolean = false
    private var jongens: Boolean = true


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.menu_searchview, menu)
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

    init {
        setHasOptionsMenu(true)
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

        checkedJongen = this.rb_jongen
        checkedMeisje = this.rb_meisje
        sportSpinner = this.spinner_sport


        spinner_postcode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                postcode = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }






        spinner_sport.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sport = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        rb_jongen.setOnClickListener {
            this.jongens = checkedJongen!!.isChecked
            this.meisjes = checkedMeisje!!.isChecked
        }

        rb_meisje.setOnClickListener{
            this.meisjes = checkedMeisje!!.isChecked
            this.jongens = checkedJongen!!.isChecked
        }

            btn_zoek.setOnClickListener { _ ->
                var sportclubs : List<Sportclub>
                viewModel.getSportclubs().observe(this, Observer {
                    if(jongens == true){
                        sportclubs =  it!!.filter {
                            club ->
                            (club.jongen == jongens)
                    }}else{
                        sportclubs =  it!!.filter {
                                club ->
                                (club.meisje == meisjes)
                        }
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

                val pairs = sportclubs.map{ club -> club.naam to club}
                val sorted = pairs.sortedWith(compareBy { club -> club.first })
                sportclubLijstFragment.filterenVanSportclubs(sorted.map{club -> club.second})
            })

            }




    }



}