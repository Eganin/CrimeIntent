package com.example.crimeintent.data.model.repositories

import android.content.Context
import androidx.room.Room
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.data.storage.CrimeDatabase
import com.example.crimeintent.data.storage.migration_1_2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
class CrimeRepository private constructor(context: Context) {


    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()

    private val crimeDao = database.crimesDao

    private val dispatcher = Dispatchers.IO

    fun getAllCrimes() = crimeDao.getAllCrimes()

    fun getCrimeById(id: UUID) = crimeDao.getCrimeById(id = id)

    suspend fun updateCrime(crime: Crime) =
        withContext(dispatcher) { crimeDao.updateCrime(crime = crime) }

    suspend fun addCrime(crime: Crime) =
        withContext(dispatcher) { crimeDao.addCrime(crime = crime) }

    suspend fun deleteCrime(crime: Crime) =
        withContext(dispatcher) { crimeDao.deleteCrime(crime = crime) }


    companion object {
        private const val DATABASE_NAME = "crime-database"
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