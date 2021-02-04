package com.example.crimeintent.ui.screens.criminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.crimeintent.R
import com.example.crimeintent.ui.presentation.criminal.utils.routing.Router
import com.example.crimeintent.ui.presentation.criminal.view.crimedetails.CrimeFragment
import com.example.crimeintent.ui.presentation.criminal.view.crimelist.CrimeListFragment
import java.util.*

class MainActivity : AppCompatActivity(), Router, CrimeListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) openCriminalList()
    }

    private fun openFragment(fragment: Fragment, addBackStack: Boolean = false) {
        val transaction =
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)

        if (addBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
        }

        transaction.commit()
    }

    override fun openCriminalTask(crimeId: UUID) =
        openFragment(fragment = CrimeFragment.newInstance(crimeId = crimeId), addBackStack = true)

    override fun openCriminalList() =
        openFragment(fragment = CrimeListFragment.newInstance(), addBackStack = true)

    override fun onCrimeSelected(crimeId: UUID) {
        openCriminalTask(crimeId = crimeId)
    }
}