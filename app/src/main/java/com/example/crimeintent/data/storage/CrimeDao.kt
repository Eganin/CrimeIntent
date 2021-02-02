package com.example.crimeintent.data.storage

import androidx.room.Dao
import androidx.room.Query
import com.example.crimeintent.data.model.entities.Crime
import java.util.*

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crimes")
    fun getAllCrimes(): List<Crime>

    @Query("SELECT * FROM crimes WHERE id = :id")
    fun getCrimeById(id: UUID): Crime?
}