package com.example.crimeintent.ui.presentation.criminal.viewmodel

import androidx.lifecycle.ViewModel
import com.example.crimeintent.data.model.entities.Crime

class CrimeListViewModel : ViewModel() {

    var crimes = mutableListOf<Crime>()

    init{
        for(i in 0 until 100){
            val crime = Crime()
            crime.title = "Crime #{$i}"
            crime.isSolved = i %2 ==0
            crimes.add(crime)
        }
    }
}