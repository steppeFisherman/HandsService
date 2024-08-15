package com.example.handsservice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.data.Item
import com.example.handsservice.databinding.ItemRawAliveBinding
import com.example.handsservice.databinding.ItemRawDeadBinding
import com.example.handsservice.databinding.ItemRawNewLifeBinding

class MainAdapter(private val context: Context) : ListAdapter<Item, MainAdapter.RecyclerViewHolder>(ItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val deadBinding = ItemRawDeadBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val aliveBinding = ItemRawAliveBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val lifeBinding = ItemRawNewLifeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return when (viewType) {
            TYPE_DEAD -> RecyclerViewHolder.DeadHolder(deadBinding)
            TYPE_ALIVE -> RecyclerViewHolder.AliveHolder(aliveBinding)
            else -> RecyclerViewHolder.NewLifeHolder(lifeBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val item = getItem(position)

        when (holder) {

            is RecyclerViewHolder.DeadHolder -> {
                holder.deadBinding.apply {
//                    root.tag = item
                    deadImg.animation = AnimationUtils
                        .loadAnimation(
                            holder.deadBinding.root.context,
                            R.anim.fade_transition_animation
                        )
                }
            }

            is RecyclerViewHolder.AliveHolder -> {
                holder.aliveBinding.apply {
//                    root.tag = item
                    aliveImg.animation = AnimationUtils
                        .loadAnimation(
                            holder.aliveBinding.root.context,
                            R.anim.fade_transition_animation
                        )
                }
            }

            is RecyclerViewHolder.NewLifeHolder -> {
                holder.lifeBinding.apply {
//                    root.tag = item
                    newLifeImg.animation = AnimationUtils
                        .loadAnimation(
                            holder.lifeBinding.root.context,
                            R.anim.fade_transition_animation
                        )
                }
            }
        }
    }


    sealed class RecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        class DeadHolder(val deadBinding: ItemRawDeadBinding) : RecyclerViewHolder(deadBinding)
        class AliveHolder(val aliveBinding: ItemRawAliveBinding) : RecyclerViewHolder(aliveBinding)
        class NewLifeHolder(val lifeBinding: ItemRawNewLifeBinding) :
            RecyclerViewHolder(lifeBinding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        val dead = ContextCompat.getString(context, R.string.dead)
        val alive = ContextCompat.getString(context, R.string.alive)
        return when (item.name) {
            dead -> TYPE_DEAD
            alive -> TYPE_ALIVE
            else -> TYPE_NEW_LIFE
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return false
        }
    }

    companion object {
        private const val TYPE_DEAD = 0
        private const val TYPE_ALIVE = 1
        private const val TYPE_NEW_LIFE = 2
    }
}