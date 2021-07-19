package com.example.sudoku.dataStatistics.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sudoku.R
import com.example.sudoku.dataStatistics.ComboWin
import com.example.sudoku.databinding.ItemRecordBinding

private const val NOW_COMBO = 0
private const val BEST_COMBO = 1

class RecordComboWinAdapter: ListAdapter<Int, RecordGameAdapter.GameViewHolder>(RecordGameAdapter.GameComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordGameAdapter.GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecordBinding.inflate(layoutInflater,parent,false)
        return RecordGameAdapter.GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordGameAdapter.GameViewHolder, position: Int) {
        val item = getItem(position)
        val resource = holder.itemView.context.resources
        holder.binding.apply {
            number.text = item.toString()
            when(position){
                NOW_COMBO->{
                    title.text = resource.getString(R.string.record_bestTime)
                }
                BEST_COMBO->{
                    title.text = resource.getString(R.string.record_averageTime)
                }

            }
        }
    }
}