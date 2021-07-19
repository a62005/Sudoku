package com.example.sudoku.dataStatistics.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sudoku.R
import com.example.sudoku.dataStatistics.record.RecordGameAdapter.*
import com.example.sudoku.databinding.ItemRecordBinding

private const val BEST_SCORE = 0
private const val AVERAGE_SCORE = 1

class RecordScoreAdapter: ListAdapter<Int, GameViewHolder>(GameComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecordBinding.inflate(layoutInflater,parent,false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = getItem(position)
        val resource = holder.itemView.context.resources
        holder.binding.apply {
            number.text = item.toString()
            when(position){
                BEST_SCORE->{
                    title.text = resource.getString(R.string.record_bestTime)
                }
                AVERAGE_SCORE->{
                    title.text = resource.getString(R.string.record_averageTime)
                }

            }
        }
    }
}