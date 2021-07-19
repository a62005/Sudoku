package com.example.sudoku.dataStatistics.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sudoku.GameViewModel
import com.example.sudoku.R
import com.example.sudoku.databinding.FragmentRecordBinding


class RecordFragment : Fragment() {

    private var binding : FragmentRecordBinding? = null
    private val viewModel : GameViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        val view = inflater.inflate(R.layout.fragment_record, container, false)
        binding = FragmentRecordBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val gameAdapter = RecordGameAdapter()
            val timeAdapter = RecordTimeAdapter()
            val scoreAdapter = RecordScoreAdapter()
            val comboWinAdapter = RecordComboWinAdapter()
            recyclerViewRecordGame.adapter = gameAdapter
            recyclerViewRecordTime.adapter = timeAdapter
            recyclerViewRecordScore.adapter = scoreAdapter
            recyclerViewRecordWinCombo.adapter = comboWinAdapter
            viewModel.game.observe(viewLifecycleOwner){
                val game = mutableListOf<Int>()
                game.add(it.playedGameTimes)
                game.add(it.playedWinTimes)
                game.add(it.winRare)
                game.add(it.noMistakeWinTimes)
                gameAdapter.submitList(game)
            }
            viewModel.time.observe(viewLifecycleOwner){
                val time = mutableListOf<String>()
                time.add(it.bestTime)
                time.add(it.averageTime)
                timeAdapter.submitList(time)
            }
            viewModel.score.observe(viewLifecycleOwner){
                val score = mutableListOf<Int>()
                score.add(it.bestScore)
                score.add(it.averageScore)
                scoreAdapter.submitList(score)
            }
            viewModel.comboWin.observe(viewLifecycleOwner){
                val comboWin = mutableListOf<Int>()
                comboWin.add(it.nowCombo)
                comboWin.add(it.bestCombo)
                comboWinAdapter.submitList(comboWin)
            }

        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}