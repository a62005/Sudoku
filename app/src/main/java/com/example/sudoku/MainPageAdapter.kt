package com.example.sudoku

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sudoku.dailyChallenge.DailyChallengeFragment
import com.example.sudoku.dataStatistics.DataStatisticsFragment
import com.example.sudoku.homePage.HomePageFragment

class MainPageAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var fragments = arrayListOf(HomePageFragment(), DailyChallengeFragment(),DataStatisticsFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}