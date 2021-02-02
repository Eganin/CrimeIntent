package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class CrimeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var crime: Crime

    private val titleTextView = itemView.findViewById<TextView>(R.id.crime_title)
    private val dateTextView = itemView.findViewById<TextView>(R.id.crime_date)
    private val solvedImageView = itemView.findViewById<ImageView>(R.id.crime_solved)

    init {
        itemView.setOnClickListener {
            Toast.makeText(itemView.context, "${crime.title} pressed!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun bind(crime: Crime) {
        this.crime = crime
        titleTextView.text = crime.title
        dateTextView.text = parseDate(date = crime.date)
        solvedImageView.isVisible = crime.isSolved
    }

    @SuppressLint("SimpleDateFormat")
    private fun parseDate(date: Date): String {
        val pattern = "MM.dd.yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(date)
    }

}