package com.example.sanderbeazar.sportinaalst.fragments

import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import com.example.sanderbeazar.sportinaalst.R

class SearchFragmentFragment : Fragment{

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear();
        inflater?.inflate(R.menu.menu_searchview, menu);

    }

    constructor(){
        setHasOptionsMenu(true);
    }



}