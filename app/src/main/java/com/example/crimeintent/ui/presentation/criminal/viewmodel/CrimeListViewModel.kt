package com.example.crimeintent.ui.presentation.criminal.viewmodel

import androidx.lifecycle.ViewModel
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.data.model.repositories.CrimeRepository

class CrimeListViewModel : ViewModel() {

    private val repository = CrimeRepository.getRepository()
    val crimes = repository.getAllCrimes()

    fun addCrime(crime : Crime)= repository.addCrime(crime=crime)

    fun deleteCrime(crime : Crime)=repository.deleteCrime(crime=crime)
}