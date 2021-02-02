package com.example.crimeintent.data.model.repositories

import android.content.Context

class CrimeRepository private constructor(context: Context) {
    companion object {
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