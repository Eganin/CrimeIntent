package com.example.crimeintent.ui.presentation.criminal.view


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime

class CrimeFragment : Fragment(R.layout.fragment_crime) {

    private lateinit var crime: Crime
    private var titleField: EditText? = null
    private var dateButton: Button? = null
    private var solvedCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view = view)
        setupListeners()

    }

    override fun onStart() {
        super.onStart()

    }

    private fun setupViews(view: View) {
        titleField = view.findViewById(R.id.crime_title)
        dateButton = view.findViewById(R.id.crime_date)
        solvedCheckBox = view.findViewById(R.id.crime_solved)
    }

    private fun setupListeners() {
        dateButton?.apply {
            text = crime.date.toString()
            isEnabled = false
        }

        solvedCheckBox?.apply {
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
            }
        }

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}

        }

        titleField?.addTextChangedListener(titleWatcher)
    }

}