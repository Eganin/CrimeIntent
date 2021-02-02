package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime

class CrimeAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CrimeHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_crime, parent, false)
        )

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) =
        holder.bind(crime = crimes[position])

    override fun getItemCount() = crimes.size

    /*
    override fun getItemViewType(position : Int) : Int{

    }

     */

}