package com.example.crimeintent.application

import android.app.Application
import com.example.crimeintent.data.model.repositories.CrimeRepository

class CriminalIntentApplication : Application() {

    override fun onCreate(){
        super.onCreate()
        CrimeRepository.initialize(context=this)
    }
}