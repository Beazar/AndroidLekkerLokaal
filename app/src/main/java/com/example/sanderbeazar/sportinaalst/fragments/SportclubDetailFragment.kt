package com.example.sanderbeazar.sportinaalst.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.domain.Sportclub


class SportclubDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sportclub_detail_fragment, container, false)
    }
    override fun onResume() {
        super.onResume()


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
