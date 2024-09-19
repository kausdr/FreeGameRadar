package com.example.recyclerviewapp.presenter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.databinding.RecyclerItemBinding
import com.example.recyclerviewapp.model.City
import com.example.recyclerviewapp.model.Singleton

class MainRecyclerViewAdapter(val itemClickListener: ItemClickListener?) :RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {
    interface ItemClickListener{
        fun onClick(view: View, position:Int)
        fun onLongClick(view: View, position:Int)
    }
    inner class ViewHolder(val binding: RecyclerItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(city: City){
            binding.apply {
                textView1.text = city.name
                textView2.text = city.population.toString()
                root.setOnClickListener {
                    itemClickListener?.onClick(binding.root,adapterPosition)
                }
                root.setOnLongClickListener {
                    itemClickListener?.onLongClick(binding.root,adapterPosition)
                    true
                }
            }
        }
    }
    var counter = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        counter++
        Log.d("Recycler","onCreateViewHolder $counter")
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Singleton.cities[position].apply {
            holder.bind(this)
        }
        Log.d("Recycler","onBindViewHolder $position")
    }
    override fun getItemCount() = Singleton.cities.size

}