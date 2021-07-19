package com.example.sudoku.homePage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.sudoku.GameViewModel
import com.example.sudoku.R
import com.example.sudoku.databinding.FragmentHomePageBinding
import com.example.sudoku.databinding.LayoutNewGameActionSheetsBinding
import com.example.sudoku.homePage.game.*
import com.example.sudoku.homePage.game.GamePageFragment
import com.google.android.material.bottomsheet.BottomSheetDialog


@Suppress("NAME_SHADOWING")
class HomePageFragment : Fragment() {

    private var binding : FragmentHomePageBinding? = null
    private val viewModel : GameViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        binding = FragmentHomePageBinding.bind(view)
        return view
    }

    @SuppressLint("InflateParams", "UseCompatLoadingForDrawables")
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
                val manager = parentFragmentManager
                buttonEasy.setOnClickListener {
                    val transaction = manager.beginTransaction()
                    val fragment = GamePageFragment.newInstance(EASY)
                    transaction.replace(R.id.layout_main, fragment, GamePageFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()
                    bottomSheetDialog.dismiss()
                }
                buttonNormal.setOnClickListener {
                    val transaction = manager.beginTransaction()
                    val fragment = GamePageFragment.newInstance(NORMAL)
                    transaction.replace(R.id.layout_main, fragment, GamePageFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()
                    bottomSheetDialog.dismiss()
                }
                buttonHard.setOnClickListener {
                    val transaction = manager.beginTransaction()
                    val fragment = GamePageFragment.newInstance(HARD)
                    transaction.replace(R.id.layout_main, fragment, GamePageFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()
                    bottomSheetDialog.dismiss()
                }
            }

            viewModel.nowGame.observe(viewLifecycleOwner){
                if(it == null){
                    buttonContinue.visibility = View.GONE
                    return@observe
                }
                buttonContinue.visibility = View.VISIBLE
                buttonNewGame.background = resources.getDrawable(R.drawable.shape_white_button,null)
                buttonNewGame.setTextColor(resources.getColor(R.color.light_blue,null))
                when(it.mode){
                    EASY->mode.text = resources.getString(R.string.newGame_easy)
                    NORMAL->mode.text = resources.getString(R.string.newGame_normal)
                    HARD->mode.text = resources.getString(R.string.newGame_hard)
                }
                time.text = it.time
            }


        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}