package com.example.crimeintent.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "crime")
data class Crime(
    @PrimaryKey
    var id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var time: String = "00:00",
    var isSolved: Boolean = false,
    var suspect : String = ""
)