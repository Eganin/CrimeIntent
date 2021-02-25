package com.example.crimeintent.ui.presentation.criminal.view.crimedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.crimeintent.R
import com.example.crimeintent.ui.presentation.criminal.utils.puctureutils.getScaledBitmap

class CrimeImageFragment : DialogFragment() {

    private var detailCrimePhoto: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crime_image, container, false)?.apply {
            setBackgroundColor(resources.getColor(R.color.transparent))
            setupViews(view = this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailCrimePhoto?.apply {
            activity?.let { activity ->
                val bitmap = getScaledBitmap(
                    path = arguments?.getString(SAVE_PATH_IMAGE) ?: "",
                    activity = activity
                )
                setImageBitmap(bitmap)
            }
        }
    }


    private fun setupViews(view: View) {
        detailCrimePhoto = view.findViewById(R.id.detail_crime_photo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailCrimePhoto = null
    }

    companion object {
        private const val SAVE_PATH_IMAGE = "SAVE_PATH_IMAGE"
        fun newInstance(path: String) = CrimeImageFragment().apply {
            arguments = Bundle().apply {
                putString(SAVE_PATH_IMAGE, path)
            }
        }
    }
}