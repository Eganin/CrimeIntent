package com.example.crimeintent.ui.screens.criminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.crimeintent.R
import com.example.crimeintent.ui.presentation.criminal.utils.routing.Router
import com.example.crimeintent.ui.presentation.criminal.view.crimedetails.CrimeFragment

class MainActivity : AppCompatActivity(), Router {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openCriminalTask()
    }

    private fun openFragment(fragment: Fragment, addBackStack: Boolean=false) {
        val transaction =
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)

        if (addBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
        }

        transaction.commit()
    }

    override fun openCriminalTask() =
        openFragment(fragment = CrimeFragment(), addBackStack = true)
}