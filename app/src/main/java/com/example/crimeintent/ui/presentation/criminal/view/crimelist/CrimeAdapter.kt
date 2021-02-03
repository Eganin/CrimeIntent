package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime

class CrimeAdapter(var callback: CrimeListFragment.Callbacks?=null) : RecyclerView.Adapter<CrimeHolder>() {
    private var crimes: List<Crime> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CrimeHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_crime, parent, false),
            callback=callback
        )

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) =
        holder.bind(crime = crimes[position])

    override fun getItemCount() = crimes.size

    fun bindCrime(data : List<Crime>){
        crimes = data
    }

}