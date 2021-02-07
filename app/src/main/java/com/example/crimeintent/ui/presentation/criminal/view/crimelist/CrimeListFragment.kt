package com.example.crimeintent.ui.presentation.criminal.view.crimelist

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.ui.presentation.criminal.viewmodel.CrimeListViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class CrimeListFragment : Fragment(R.layout.fragment_crime_list) {

    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callback: Callbacks? = null
    private var crimeRecyclerView: RecyclerView? = null
    private var adapter: CrimeAdapter? = null
    private var coordinatorLayout: CoordinatorLayout? = null

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this)[CrimeListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)// вызов меню
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view = view)
        context?.let { setupRecyclerView(context = it) }
        setupTouchListener()
        crimeListViewModel.crimes.observe(viewLifecycleOwner, ::updateAdapter)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callbacks) {
            callback = context
            adapter = CrimeAdapter().apply { callback = context }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        crimeRecyclerView = null
        coordinatorLayout = null
        crimeRecyclerView?.adapter = null
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.new_crime -> {
            val crime = Crime()
            crimeListViewModel.addCrime(crime = crime)
            callback?.onCrimeSelected(crimeId = crime.id)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    private fun setupView(view: View) {
        crimeRecyclerView = view.findViewById(R.id.crime_list)
        coordinatorLayout = view.findViewById(R.id.main_coordinator)
    }

    private fun setupRecyclerView(context: Context) {
        crimeRecyclerView?.layoutManager = LinearLayoutManager(context)
        crimeRecyclerView?.adapter = adapter
    }

    private fun updateAdapter(data: List<Crime>) {
        adapter?.bindCrime(data = data)
        adapter?.notifyDataSetChanged()
        if (adapter?.itemCount==0) showSnackBar(text = "Crimes is not exists")
    } 

    private fun showSnackBar(text: String = "") =
        coordinatorLayout?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG).show() }

    private fun setupTouchListener() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val crime = adapter?.getCrimes()?.get(viewHolder.adapterPosition)
                crime?.let { crimeListViewModel.deleteCrime(it) }
            }

        }).attachToRecyclerView(crimeRecyclerView)
    }

}