package com.example.crimeintent.ui.presentation.criminal.utils.diffutill

import androidx.recyclerview.widget.DiffUtil
import com.example.crimeintent.data.model.entities.Crime

class DiffUtillCallback(
    private val oldList: List<Crime>,
    private val newList: List<Crime>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newCrime = newList[newItemPosition]
        val oldCrime = oldList[oldItemPosition]
        return newCrime.date == oldCrime.date &&
                newCrime.time == oldCrime.time &&
                newCrime.title == oldCrime.title &&
                newCrime.isSolved == oldCrime.isSolved
    }
}