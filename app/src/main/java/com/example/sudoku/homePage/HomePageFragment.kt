package com.example.sudoku.homePage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sudoku.R
import com.example.sudoku.databinding.FragmentHomePageBinding
import com.example.sudoku.databinding.LayoutNewGameActionSheetsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class HomePageFragment : Fragment() {

    private var binding : FragmentHomePageBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        binding = FragmentHomePageBinding.bind(view)
        return view
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val bottomSheetDialog = BottomSheetDialog(requireContext()) //初始化BottomSheet
            val view: View = LayoutInflater.from(requireContext()).inflate(R.layout.layout_new_game_action_sheets, null) //連結的介面
            val buttonBinding = LayoutNewGameActionSheetsBinding.bind(view)
            bottomSheetDialog.setContentView(view) //將介面載入至BottomSheet內
            val parent = view.parent as ViewGroup //取得BottomSheet介面設定
            parent.setBackgroundResource(android.R.color.transparent) //將背景設為透明，否則預設白底

            buttonNewGame.setOnClickListener {
                bottomSheetDialog.show()
            }
            buttonBinding.apply {
                buttonCancel.setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
            }


        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}