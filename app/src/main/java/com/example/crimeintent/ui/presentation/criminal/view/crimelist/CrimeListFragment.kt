package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.ui.presentation.criminal.viewmodel.CrimeListViewModel
import java.util.*

class CrimeListFragment : Fragment(R.layout.fragment_crime_list) {

    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callback: Callbacks? = null
    private var crimeRecyclerView: RecyclerView? = null
    private var adapter: CrimeAdapter? = null
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this)[CrimeListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view = view)
        context?.let { setupRecyclerView(context = it) }
        crimeListViewModel.crimes.observe(viewLifecycleOwner, ::updateAdapter)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callbacks) {
            callback = context
            adapter = CrimeAdapter().apply {callback=context}
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    private fun setupView(view: View) {
        crimeRecyclerView = view.findViewById(R.id.crime_list)
    }

    private fun setupRecyclerView(context: Context) {
        crimeRecyclerView?.layoutManager = LinearLayoutManager(context)
        crimeRecyclerView?.adapter = adapter
    }

    private fun updateAdapter(data: List<Crime>) {
        adapter?.bindCrime(data = data)
        adapter?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }
}