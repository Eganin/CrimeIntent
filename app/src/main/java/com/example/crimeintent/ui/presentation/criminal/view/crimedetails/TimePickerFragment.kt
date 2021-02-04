package com.example.crimeintent.ui.presentation.criminal.view.crimedetails

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class TimePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onTimeSelected(time: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val timeListener = setupTimeListener()
        val time = arguments?.getSerializable(ARG_TIME) as String
        val (hour , minute) = time.split(":").map { it.toInt() }

        return TimePickerDialog(
            requireContext(),
            timeListener,
            hour,
            minute,
            true
        )
    }

    private fun setupTimeListener() =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay: Int, minute: Int ->
            val resultTime = "${hourOfDay}:${minute}"
            targetFragment?.let {
                (it as Callbacks).onTimeSelected(time = resultTime)
            }
        }

    companion object {
        private const val ARG_TIME = "ARG_TIME"
        fun newInstance(time: String): TimePickerFragment {
            val args = Bundle().apply {
                putString(ARG_TIME, time)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}