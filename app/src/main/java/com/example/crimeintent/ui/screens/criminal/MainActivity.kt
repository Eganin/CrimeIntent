package com.example.crimeintent.ui.screens.criminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.crimeintent.R
import com.example.crimeintent.ui.presentation.criminal.utils.routing.Router
import com.example.crimeintent.ui.presentation.criminal.view.crimedetails.CrimeFragment
import com.example.crimeintent.ui.presentation.criminal.view.crimelist.CrimeListFragment
import java.util.*

class MainActivity : AppCompatActivity(), Router, CrimeListFragment.Callbacks {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        if (savedInstanceState == null) openCriminalList()
    }

    override fun openCriminalTask(crimeId: UUID) {
        val args = Bundle().apply {
            putSerializable(CrimeFragment.ARG_CRIME_ID,crimeId)
        }
        navController?.navigate(R.id.action_crimeListFragment_to_crimeFragment,args)
    }


    override fun openCriminalList() {
        navController?.navigate(R.id.crimeListFragment)
    }


    override fun onCrimeSelected(crimeId: UUID)=openCriminalTask(crimeId=crimeId)
}