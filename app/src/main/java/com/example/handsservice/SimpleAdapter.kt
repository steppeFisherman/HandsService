package com.example.handsservice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.data.Item
import com.example.handsservice.databinding.ItemRawAliveBinding
import com.example.handsservice.databinding.ItemRawDeadBinding
import com.example.handsservice.databinding.ItemRawNewLifeBinding

class SimpleAdapter(private val context: Context) :
    RecyclerView.Adapter<SimpleAdapter.RecyclerViewHolder>() {

    private var mList = emptyList<Item>()

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

        when (holder) {
            is RecyclerViewHolder.DeadHolder -> holder.deadBinding
            is RecyclerViewHolder.AliveHolder -> holder.aliveBinding
            is RecyclerViewHolder.NewLifeHolder -> holder.lifeBinding
        }
    }

    override fun getItemCount(): Int = mList.size

    fun submitList(list: List<Item>) {
        mList = list
    }

    sealed class RecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        class DeadHolder(val deadBinding: ItemRawDeadBinding) : RecyclerViewHolder(deadBinding)
        class AliveHolder(val aliveBinding: ItemRawAliveBinding) : RecyclerViewHolder(aliveBinding)
        class NewLifeHolder(val lifeBinding: ItemRawNewLifeBinding) :
            RecyclerViewHolder(lifeBinding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = mList[position]
        val dead = ContextCompat.getString(context, R.string.dead)
        val alive = ContextCompat.getString(context, R.string.alive)
        return when (item.name) {
            dead -> TYPE_DEAD
            alive -> TYPE_ALIVE
            else -> TYPE_NEW_LIFE
        }
    }

    companion object {
        private const val TYPE_DEAD = 0
        private const val TYPE_ALIVE = 1
        private const val TYPE_NEW_LIFE = 2
    }
}