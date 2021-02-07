package com.example.crimeintent.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.crimeintent.data.model.entities.Crime

@Database(entities = [Crime::class], version = 1)
@TypeConverters(CrimeTypeConverter::class)
abstract class CrimeDatabase : RoomDatabase(){

    abstract val crimesDao : CrimeDao

}

val migration = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) = database.execSQL("ALTER TABLE crime ADD COLUMN suspect TEXT NOT NULL DEFAULT ''")
}