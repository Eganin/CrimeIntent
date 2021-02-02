package com.example.crimeintent.data.model.repositories

import android.content.Context
import androidx.room.Room
import com.example.crimeintent.data.storage.CrimeDatabase
import java.util.*

class CrimeRepository private constructor(context: Context) {


    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val crimeDao = database.crimesDao

    fun getAllCrimes() = crimeDao.getAllCrimes()

    fun getCrimeById(id: UUID) = crimeDao.getCrimeById(id = id)

    companion object {
        private const val DATABASE_NAME = "crime-db"
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun getRepository(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository not initalizated")
        }
    }
}