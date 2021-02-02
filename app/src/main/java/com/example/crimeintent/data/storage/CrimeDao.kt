package com.example.crimeintent.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.crimeintent.data.model.entities.Crime
import java.util.*

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getAllCrimes(): LiveData<List<Crime>>

    @Query("SELECT * FROM crime WHERE id = :id")
    fun getCrimeById(id: UUID): LiveData<Crime?>
}