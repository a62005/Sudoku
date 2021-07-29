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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("LABEL_NAME_CLASH")
class SmallFrameAdapter(private val bigView: View, private val viewModel: GameViewModel, private val lifecycleOwner: LifecycleOwner): ListAdapter<Int, SmallFrameAdapter.SmallFrameViewHolder>(SmallFrameComparator()){
    private val scope = CoroutineScope(Dispatchers.IO)
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
                    viewModel.setFrameOnclick(bigView,this,holder.binding)
                }
                viewModel.bigFrameOnClick.observe(lifecycleOwner){big->
                    viewModel.smallFrameOnClick.observe(lifecycleOwner){small->
                        scope.launch {
                            when {
                                small == this@apply -> {
                                    background = resource.getDrawable(R.drawable.shape_small_frame_focus)
                                }
                                bigView.x == big.x -> {
                                    background = if(this@apply.x == small.x) {
                                        resource.getDrawable(R.drawable.shape_small_frame_onclick)
                                    }else{
                                        resource.getDrawable(R.drawable.shape_small_frame)
                                    }
                                }
                                bigView.y == big.y -> {
                                    background = if(this@apply.y == small.y) {
                                        resource.getDrawable(R.drawable.shape_small_frame_onclick)
                                    }else{
                                        resource.getDrawable(R.drawable.shape_small_frame)
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
}