package com.example.sudoku.homePage.game

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sudoku.PlayingGame
import com.example.sudoku.GameViewModel
import com.example.sudoku.R
import com.example.sudoku.databinding.FragmentGamePageBinding
import kotlin.collections.ArrayList

private const val MODE = "MODE"
const val EASY = "EASY"
const val NORMAL = "NORMAL"
const val HARD = "HARD"

class GamePageFragment : Fragment() {

    private var mode: String? = null
    private var binding : FragmentGamePageBinding? = null
    private val viewModel : GameViewModel by activityViewModels()
    private var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mode = it.getString(MODE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_game_page, container, false)
        binding = FragmentGamePageBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            when(mode){
                EASY->textViewMode.text = requireContext().resources.getString(R.string.newGame_easy)
                NORMAL->textViewMode.text = requireContext().resources.getString(R.string.newGame_normal)
                HARD->textViewMode.text = requireContext().resources.getString(R.string.newGame_hard)
            }

            timer.base = SystemClock.elapsedRealtime();//計時器清零
            val hour = SystemClock.elapsedRealtime() - timer.base
            timer.format = "0${(hour / 1000 / 60).toInt()}:%s"
            timer.start()

            var pauseTime = 0L
            buttonPlayStatus.setOnClickListener {
                if(isPlaying){
                    buttonPlayStatus.setImageResource(R.drawable.play_arrow)
                    pauseTime = SystemClock.elapsedRealtime() - timer.base //紀錄按下暫停時的進行秒數
                    timer.stop()
                }else{
                    buttonPlayStatus.setImageResource(R.drawable.pause)
                    timer.base = SystemClock.elapsedRealtime() - pauseTime //讓計時器的時間更新為按下暫停時的時間
                    val startTime = SystemClock.elapsedRealtime() - timer.base
                    timer.format = "0${(startTime / 1000 / 60).toInt()}:%s"
                    timer.start()
                }
                isPlaying = !isPlaying
            }

            val manager = GridLayoutManager(requireContext(),9)
            val numberArray : ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9)
            val numberAdapter = NumberAdapter()
            numberAdapter.submitList(numberArray)
            recyclerViewNumber.layoutManager = manager
            recyclerViewNumber.adapter = numberAdapter
            val itemDecoration = GridSpacingItemVerticalDecoration(9,36,false)
            recyclerViewNumber.addItemDecoration(itemDecoration)

            buttonBack.setOnClickListener {
                val game = PlayingGame(mode!!,timer.text.toString())
                viewModel.setNowGame(game)
                activity?.onBackPressed()
            }

            val frameAdapter = LargeFrameAdapter(viewModel,viewLifecycleOwner)
            val viewArray : ArrayList<RecyclerView> = arrayListOf()
            for(i in 0 until 9){
                viewArray.add(recyclerViewLargeFrame)
            }
            frameAdapter.submitList(viewArray)
            recyclerViewLargeFrame.adapter = frameAdapter

        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun newInstance(mode: String) =
            GamePageFragment().apply {
                arguments = Bundle().apply {
                    putString(MODE, mode)
                }
            }
    }
}