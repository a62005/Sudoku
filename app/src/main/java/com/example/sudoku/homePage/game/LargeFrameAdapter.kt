package com.example.sudoku.homePage.game

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sudoku.GameViewModel
import com.example.sudoku.R
import com.example.sudoku.databinding.ItemLargeFrameNumberBinding


class LargeFrameAdapter(private val viewModel: GameViewModel,private val lifecycleOwner: LifecycleOwner) : ListAdapter<RecyclerView,LargeFrameAdapter.LargeFrameViewHolder>(LargeComparator()){
    class LargeFrameViewHolder(val binding: ItemLargeFrameNumberBinding):RecyclerView.ViewHolder(binding.root)
    class LargeComparator:DiffUtil.ItemCallback<RecyclerView>(){
        override fun areItemsTheSame(oldItem: RecyclerView, newItem: RecyclerView): Boolean {
            return oldItem === newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: RecyclerView, newItem: RecyclerView): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeFrameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLargeFrameNumberBinding.inflate(layoutInflater,parent,false)
        return LargeFrameViewHolder(binding)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: LargeFrameViewHolder, position: Int) {
        val resource = holder.itemView.context
        holder.binding.apply {
            holder.itemView.apply {
                val numberArray : ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9)
                val numberAdapter = SmallFrameAdapter(this,viewModel,lifecycleOwner)
                numberAdapter.submitList(numberArray)
                recyclerViewSmallFrame.adapter = numberAdapter
                viewModel.bigFrameOnClick.observe(lifecycleOwner){
                    background = if(this == it){
                        resource.getDrawable(R.drawable.shape_large_frame_onclick)
                    }else{
                        resource.getDrawable(R.drawable.shape_large_frame)
                    }
                }
            }
        }
    }


}
