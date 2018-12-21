package com.example.sanderbeazar.sportinaalst.domain

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.sanderbeazar.sportinaalst.R
import com.example.sanderbeazar.sportinaalst.fragments.SportclubDetailFragment
import com.example.sanderbeazar.sportinaalst.fragments.SportclubLijstFragment
import kotlinx.android.synthetic.main.sportclub_lijst_content.view.*

class SimpleItemRecyclerViewAdapter(private val parentActivity: SportclubLijstFragment,
                                    private val sportclubs: List<Sportclub>) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            // Every view has a tag that can be used to store data related to that view
            // Here each item in the RecyclerView keeps a reference to the comic it represents.
            // This allows us to reuse a single listener for all items in the list
            val item = v.tag as Sportclub
            parentActivity.startNewActivityForDetail(item);
/*
            val intent = Intent(v.context, SportclubDetailFragment::class.java).apply {
                putExtra(SportclubDetailFragment.ARG_SPORTCLUB, item)
                parentActivity.startNewActivityForDetail(item)
*/
//}
      //      }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.sportclub_lijst_content, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sportclub = sportclubs!![position]
        holder.name.text = sportclub.naam
        holder.sport.text = sportclub.sport
        holder.adres.text = sportclub.adres + ", " + sportclub.Postcode

        with(holder.itemView) {
            tag = sportclub // Save the comic represented by this view
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = sportclubs!!.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.lijstnaam
        val sport: TextView = view.sport
        val adres: TextView = view.adres
    }
}