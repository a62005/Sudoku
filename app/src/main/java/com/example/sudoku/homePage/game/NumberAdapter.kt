package com.example.sudoku.homePage.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sudoku.databinding.ItemNumberBinding

class NumberAdapter: ListAdapter<Int, NumberAdapter.NumberViewHolder>(NumberComparator()) {
    class NumberViewHolder(val binding: ItemNumberBinding):RecyclerView.ViewHolder(binding.root)
    class NumberComparator:DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNumberBinding.inflate(layoutInflater,parent,false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.binding.apply {
            number.text = getItem(position).toString()
        }
    }
}