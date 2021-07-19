package com.example.sudoku.dataStatistics

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sudoku.GameViewModel
import com.example.sudoku.MainPageAdapter
import com.example.sudoku.R
import com.example.sudoku.databinding.FragmentDataStatisticsBinding
import com.example.sudoku.databinding.FragmentHomePageBinding
import com.google.android.material.tabs.TabLayoutMediator


class DataStatisticsFragment : Fragment() {

    private var binding : FragmentDataStatisticsBinding? = null
    private val viewModel : GameViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        val view = inflater.inflate(R.layout.fragment_data_statistics, container, false)
        binding = FragmentDataStatisticsBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setRecord()
        binding?.apply {
            val title : ArrayList<String> = arrayListOf(
                resources.getString(R.string.newGame_easy),
                resources.getString(R.string.newGame_normal),
                resources.getString(R.string.newGame_hard),
                resources.getString(R.string.main_dailyChallenge)
            )

            val pageAdapter = RecordPageAdapter(parentFragmentManager, lifecycle)
            viewPage2Record.adapter = pageAdapter
            TabLayoutMediator(tabLayoutRecord,viewPage2Record){  tab, position->
                tab.text = title[position]
            }.attach()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}