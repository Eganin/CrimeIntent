package com.example.crimeintent.ui.presentation.criminal.utils.routing

import java.util.*

interface Router {

    fun openCriminalTask(crimeId : UUID)

    fun openCriminalList()
}