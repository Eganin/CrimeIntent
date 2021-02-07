package com.example.crimeintent.ui.presentation.criminal.view.crimedetails


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.crimeintent.R
import com.example.crimeintent.data.model.entities.Crime
import com.example.crimeintent.ui.presentation.criminal.viewmodel.CrimeDetailViewModel
import android.text.format.DateFormat
import java.util.*

class CrimeFragment : Fragment(R.layout.fragment_crime), DatePickerFragment.Callbacks,
    TimePickerFragment.Callbacks {

    private lateinit var crime: Crime
    private var titleField: EditText? = null
    private var dateButton: Button? = null
    private var solvedCheckBox: CheckBox? = null
    private var timeButton: Button? = null
    private var suspectButton: Button? = null
    private var reportButton: Button? = null

    private val crimeDetailViewModel: CrimeDetailViewModel by lazy {
        ViewModelProvider(this)[CrimeDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
        val crimeId = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        crimeDetailViewModel.loadCrime(crimeId = crimeId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view = view)
        setupListeners()
        crimeDetailViewModel.crimeLiveData.observe(viewLifecycleOwner, {
            it?.let { crime ->
                this.crime = crime
                updateUI()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        crimeDetailViewModel.saveCrime(crime = crime)
    }

    override fun onDateSelected(date: Date) {
        crime.date = date
        updateUI()
    }

    override fun onTimeSelected(time: String) {
        crime.time = time
        updateUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        titleField = null
        dateButton = null
        timeButton = null
        solvedCheckBox = null
        reportButton = null
        suspectButton = null
    }

    private fun setupViews(view: View) {
        titleField = view.findViewById(R.id.crime_title)
        dateButton = view.findViewById(R.id.crime_date)
        solvedCheckBox = view.findViewById(R.id.crime_solved)
        timeButton = view.findViewById(R.id.crime_time)
        reportButton = view.findViewById(R.id.crime_report)
        suspectButton = view.findViewById(R.id.crime_suspect)
    }

    private fun setupListeners() {
        dateButton?.setOnClickListener {
            DatePickerFragment.newInstance(crime.date).apply {
                setTargetFragment(this@CrimeFragment, REQUEST_DATE)
                show(this@CrimeFragment.parentFragmentManager, DIALOG_DATE)
            }
        }

        solvedCheckBox?.apply {
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
            }
        }

        timeButton?.setOnClickListener {
            TimePickerFragment.newInstance(crime.time).apply {
                setTargetFragment(this@CrimeFragment, REQUEST_TIME)
                show(this@CrimeFragment.parentFragmentManager, DIALOG_TIME)
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

        reportButton?.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getCrimeReport())
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_suspect))
            }.also { intent -> startActivity(intent) }
        }
    }

    private fun updateUI() {
        titleField?.setText(crime.title)
        dateButton?.text = crime.date.toString()
        timeButton?.text = crime.time
        solvedCheckBox?.apply {
            isChecked = crime.isSolved
            //пропуск анимации
            jumpDrawablesToCurrentState()
        }
    }

    private fun getCrimeReport(): String {
        val solved =
            if (crime.isSolved) getString(R.string.crime_report_solved)
            else getString(R.string.crime_report_unsolved)

        val dateTime = DateFormat.format(DATE_FORMAT, crime.date).toString()

        val suspect =
            if (crime.suspect.isBlank()) getString(R.string.crime_report_no_suspect)
            else getString(R.string.crime_report_suspect)

        return getString(R.string.crime_report, crime.title, dateTime, solved, suspect)
    }

    companion object {
        private const val REQUEST_TIME = 1
        private const val REQUEST_DATE = 0
        private const val DIALOG_DATE = "DialogDate"
        private const val DIALOG_TIME = "DialogTime"
        const val ARG_CRIME_ID = "crime_id"
        private const val DATE_FORMAT = "EEE, MMM, dd"
    }
}