package com.example.to_doapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapp.databinding.ItemRowBinding

class To_Do_Adapter (private val dailyTasks: ArrayList<taskModel>): RecyclerView.Adapter<To_Do_Adapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dTasks = dailyTasks[position]
        holder.binding.apply {
            tvTask.text = dTasks.text
            CB.isChecked = dTasks.checked

            CB.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    tvTask.setTextColor(Color.GRAY)
                }else{
                    tvTask.setTextColor(Color.BLACK)
                }
                dTasks.checked = !dTasks.checked
            }
        }

    }
    override fun getItemCount() = dailyTasks.size
}