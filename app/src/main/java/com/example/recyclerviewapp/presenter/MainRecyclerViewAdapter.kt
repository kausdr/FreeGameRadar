package com.example.recyclerviewapp.presenter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.databinding.RecyclerItemBinding
import com.example.recyclerviewapp.model.Filme
import com.example.recyclerviewapp.view.Detail

class MainRecyclerViewAdapter(val itemClickListener: ItemClickListener?) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    var filmes = mutableListOf<Filme>()

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }

    inner class ViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filme: Filme) {
            binding.apply {
                textView1.text = filme.title
                textView2.text = filme.overview
                root.setOnClickListener {

                    val context = root.context
                    val intent = Intent(context, Detail::class.java).apply {
                        putExtra("movie_title", filme.title)
                        putExtra("movie_description", filme.overview)
                    }
                    context.startActivity(intent)

                    itemClickListener?.onClick(binding.root, adapterPosition)
                }
                root.setOnLongClickListener {
                    itemClickListener?.onLongClick(binding.root, adapterPosition)
                    true
                }
            }
        }
    }

    fun addAll(filmes: List<Filme>) {
        this.filmes.clear()
        this.filmes.addAll(filmes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        filmes[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount() = filmes.size
}
