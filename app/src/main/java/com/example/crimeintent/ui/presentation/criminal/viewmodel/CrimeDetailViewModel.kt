package com.example.crimeintent.ui.presentation.criminal.viewmodel

import androidx.lifecycle.*
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.data.model.repositories.CrimeRepository
import kotlinx.coroutines.launch
import java.util.*

class CrimeDetailViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.getRepository()
    private val crimeIdLiveData = MutableLiveData<UUID>()

    var crimeLiveData: LiveData<Crime?> = Transformations.switchMap(crimeIdLiveData) {
        crimeRepository.getCrimeById(id = it)
    }

    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) = viewModelScope.launch { crimeRepository.updateCrime(crime) }
}