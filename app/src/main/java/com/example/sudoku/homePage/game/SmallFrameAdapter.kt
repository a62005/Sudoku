package com.example.sudoku.homePage.game

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sudoku.GameViewModel
import com.example.sudoku.R
import com.example.sudoku.databinding.ItemSmallFrameNumberBinding

class SmallFrameAdapter(private val bigView: View, private val bigFramePosition: Int, private val viewModel: GameViewModel, private val lifecycleOwner: LifecycleOwner): ListAdapter<Int, SmallFrameAdapter.SmallFrameViewHolder>(SmallFrameComparator()){
    class SmallFrameViewHolder(val binding: ItemSmallFrameNumberBinding): RecyclerView.ViewHolder(binding.root)
    class SmallFrameComparator: DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallFrameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSmallFrameNumberBinding.inflate(layoutInflater,parent,false)
        return SmallFrameViewHolder(binding)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: SmallFrameViewHolder, position: Int) {
        val resource = holder.itemView.context
        holder.binding.apply {
            holder.itemView.apply {
                setOnClickListener {
                    viewModel.setFrameOnclick(bigView,this)
                }
                viewModel.bigFrameOnClick.observe(lifecycleOwner){big->
                    viewModel.smallFrameOnClick.observe(lifecycleOwner){small->
                        when {
                            bigView.x == big.x -> {
                                if(this.x == small.x) {
                                    this.background = resource.getDrawable(R.drawable.shape_small_frame_onclick)
                                }else{
                                    background = resource.getDrawable(R.drawable.shape_small_frame)
                                }
                            }
                            bigView.y == big.y -> {
                                if(this.y == small.y) {
                                    this.background = resource.getDrawable(R.drawable.shape_small_frame_onclick)
                                }else{
                                    background = resource.getDrawable(R.drawable.shape_small_frame)
                                }
                            }
                            else -> {
                                background = resource.getDrawable(R.drawable.shape_small_frame)
                            }
                        }
                    }
                }
            }
        }
    }
}