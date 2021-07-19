package com.example.sudoku.dataStatistics.record

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sudoku.R
import com.example.sudoku.databinding.ItemRecordBinding

private const val PLAYED_GAME_TIMES = 0
private const val PLAYED_WIN_TIMES = 1
private const val WIN_RARE = 2
private const val NO_MISTAKE_WIN_TIMES = 3

class RecordGameAdapter: ListAdapter<Int, RecordGameAdapter.GameViewHolder>(GameComparator()) {

    class GameViewHolder(val binding: ItemRecordBinding):RecyclerView.ViewHolder(binding.root)
    class GameComparator:DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecordBinding.inflate(layoutInflater,parent,false)
        return GameViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = getItem(position)
        val resource = holder.itemView.context.resources
        holder.binding.apply {
            number.text = item.toString()
            when(position){
                PLAYED_GAME_TIMES->{
                    title.text = resource.getString(R.string.record_playedGameTimes)

                }
                PLAYED_WIN_TIMES->{
                    title.text = resource.getString(R.string.record_playedWinTimes)
                }
                WIN_RARE->{
                    title.text = resource.getString(R.string.record_winRare)
                    number.text = "${item}%"
                }
                NO_MISTAKE_WIN_TIMES->{
                    title.text = resource.getString(R.string.record_noMistakeWinTimes)
                }
            }
        }
    }
}