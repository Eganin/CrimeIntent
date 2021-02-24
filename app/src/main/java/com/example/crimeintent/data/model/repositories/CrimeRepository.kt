package com.example.crimeintent.data.model.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.room.Room
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.data.storage.CrimeDatabase
import com.example.crimeintent.data.storage.migration_1_2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class CrimeRepository private constructor(val context: Context) {


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

    fun getSuspect(data: Intent?) : String? {
        var result : String?=null
        val contactUri: Uri? = data?.data
        // поле имени для данных
        val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
        val cursor = context.contentResolver.query(
            contactUri!!,
            queryFields,
            null,
            null,
            null
        )
        cursor?.use {
            if (it.count != 0){
                it.moveToNext()
                result = it.getString(0)
            }
        }

        return result
    }


    companion object {
        private const val DATABASE_NAME = "crime-database"
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun getRepository() =
            INSTANCE ?: throw IllegalStateException("CrimeRepository not initalizated")

    }
}