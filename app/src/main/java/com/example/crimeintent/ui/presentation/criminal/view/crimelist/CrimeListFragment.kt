package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.crimeintent.R
import com.example.crimeintent.ui.presentation.criminal.viewmodel.CrimeListViewModel

class CrimeListFragment : Fragment() {
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this)[CrimeListViewModel::class.java]
    }


    companion object{
        fun newInstance() : CrimeListFragment{
            return CrimeListFragment()
        }
    }
}