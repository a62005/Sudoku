package com.example.sudoku

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sudoku.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            //設定Bottom viewpager
            val icon: ArrayList<Drawable> = arrayListOf(
                resources.getDrawable(R.drawable.home,null),
                resources.getDrawable(R.drawable.date,null),
                resources.getDrawable(R.drawable.data,null)
            )
            val title : ArrayList<String> = arrayListOf(
                resources.getString(R.string.main_homePage),
                resources.getString(R.string.main_dailyChallenge),
                resources.getString(R.string.main_dataStatistics)
            )

            binding.apply {
                val pageAdapter = MainPageAdapter(supportFragmentManager, lifecycle)
                viewPage2Main.adapter = pageAdapter
                TabLayoutMediator(tabLayoutBottomBar,viewPage2Main){  tab, position->
                    tab.icon = icon[position]
                    tab.text = title[position]
                }.attach()
                viewPage2Main.isUserInputEnabled = false
            }
        }
    }
}