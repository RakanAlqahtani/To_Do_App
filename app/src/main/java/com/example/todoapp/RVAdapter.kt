package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding

class RVAdapter(private val message : ArrayList<ToDoItem>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context)
            ,parent
                ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = message[position]
        holder.binding.apply {
            tvItem.text = item.text
            cbItem.isChecked = item.checked
            cbItem.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    tvItem.setTextColor(Color.GRAY)
                }else{
                    tvItem.setTextColor(Color.BLACK)
                }
                item.checked = !item.checked
            }
        }
    }

    override fun getItemCount() = message.size

    fun deleteItems(){
        message.removeAll{ item -> item.checked}
        notifyDataSetChanged()
    }

}