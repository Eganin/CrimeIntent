package com.example.crimeintent.ui.presentation.criminal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.data.model.repositories.CrimeRepository
import kotlinx.coroutines.launch

class CrimeListViewModel : ViewModel() {

    private val repository = CrimeRepository.getRepository()
    val crimes = repository.getAllCrimes()

    fun addCrime(crime: Crime) = viewModelScope.launch {
        repository.addCrime(crime = crime)
    }

    fun deleteCrime(crime: Crime) = viewModelScope.launch { repository.deleteCrime(crime = crime) }

}