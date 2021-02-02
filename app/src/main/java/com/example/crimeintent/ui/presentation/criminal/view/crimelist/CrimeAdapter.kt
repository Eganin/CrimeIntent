package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime

class CrimeAdapter : RecyclerView.Adapter<CrimeHolder>() {
    private val crimes: MutableList<Crime> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CrimeHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_crime, parent, false)
        )

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) =
        holder.bind(crime = crimes[position])

    override fun getItemCount() = crimes.size

    fun bindCrime(data : List<Crime>){
        crimes.addAll(data)
    }

}