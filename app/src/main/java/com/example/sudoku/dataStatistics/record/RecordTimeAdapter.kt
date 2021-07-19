package com.example.sudoku.dataStatistics.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sudoku.R
import com.example.sudoku.databinding.ItemRecordBinding

private const val BEST_TIME = 0
private const val AVERAGE_TIME = 1

class RecordTimeAdapter: ListAdapter<String, RecordTimeAdapter.TimeViewHolder>(TimeComparator()) {
    class TimeViewHolder(val binding: ItemRecordBinding): RecyclerView.ViewHolder(binding.root)
    class TimeComparator: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecordBinding.inflate(layoutInflater,parent,false)
        return TimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val item = getItem(position)
        val resource = holder.itemView.context.resources
        holder.binding.apply {
            number.text = item
            when(position){
                BEST_TIME->{
                    title.text = resource.getString(R.string.record_bestTime)
                }
                AVERAGE_TIME->{
                    title.text = resource.getString(R.string.record_averageTime)
                }

            }
        }
    }
}