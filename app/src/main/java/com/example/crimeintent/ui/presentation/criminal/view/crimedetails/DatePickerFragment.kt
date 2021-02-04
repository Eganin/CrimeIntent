package com.example.crimeintent.ui.presentation.criminal.view.crimedetails


import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onDateSelected(date: Date)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // слушатель даты
        val dateListener = setupDateListener()
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    private fun setupDateListener() =
        DatePickerDialog.OnDateSetListener { _ , year: Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time

            // отправляем данные в родительский фрагмент
            targetFragment?.let {
                (it as Callbacks).onDateSelected(resultDate)
            }
        }

    companion object {
        private const val ARG_DATE = "date"
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}