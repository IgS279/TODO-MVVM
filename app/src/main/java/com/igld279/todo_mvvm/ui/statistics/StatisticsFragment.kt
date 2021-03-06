package com.igld279.todo_mvvm.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.igld279.todo_mvvm.R
import org.koin.android.ext.android.inject

class StatisticsFragment : Fragment() {

    private val statisticsViewModel: StatisticsViewModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)

        val textStatisticsAll: TextView = root.findViewById(R.id.textStatisticsAll)
        statisticsViewModel.textAll().observe(viewLifecycleOwner, Observer {
            textStatisticsAll.text = it
        })

        val textStatisticsActive: TextView = root.findViewById(R.id.textStatisticsActive)
        statisticsViewModel.textActive().observe(viewLifecycleOwner, Observer {
            textStatisticsActive.text = it
        })

        val textStatisticsCompleted: TextView = root.findViewById(R.id.textStatisticsCompleted)
        statisticsViewModel.textCompleted().observe(viewLifecycleOwner, Observer {
            textStatisticsCompleted.text = it
        })

        return root
    }
}