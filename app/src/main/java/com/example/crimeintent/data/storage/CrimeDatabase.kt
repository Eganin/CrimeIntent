package com.example.crimeintent.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.crimeintent.data.model.entities.Crime

@Database(entities = [Crime::class], version = 1)
@TypeConverters(CrimeTypeConverter::class)
abstract class CrimeDatabase : RoomDatabase(){

    abstract val crimesDao : CrimeDao

}