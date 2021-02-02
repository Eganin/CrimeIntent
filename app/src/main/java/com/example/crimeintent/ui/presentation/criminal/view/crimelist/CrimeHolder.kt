package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime

class CrimeHolder(itemView : View):  RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.findViewById<TextView>(R.id.crime_title)
    private val dateTextView = itemView.findViewById<TextView>(R.id.crime_date)

    fun bind(crime : Crime){
        titleTextView.text = crime.title
        dateTextView.text = crime.date.toString()
    }
}