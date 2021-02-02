package com.example.crimeintent.data.storage

import androidx.room.TypeConverter
import java.util.*

class CrimeTypeConverter {

    @TypeConverter
    fun fromDate(date: Date?) = date?.time

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?) = millisSinceEpoch?.let { Date(it) }

    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()

    @TypeConverter
    fun toUUID(uuid: String) = UUID.fromString(uuid)

}