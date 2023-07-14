package com.androdevelopment.electronicse_commerce.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.androdevelopment.electronicse_commerce.Models.HistoryModel
import com.androdevelopment.electronicse_commerce.databinding.ItemHistoryBinding

class HistoryAdapter(private val list: List<HistoryModel>, private val onHistoryClick: OnHistoryClick) : Adapter<HistoryAdapter.HistoryViewHolder>() {

    private lateinit var binding:ItemHistoryBinding

    inner class HistoryViewHolder(binding:ItemHistoryBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = list[position]

        binding.textViewSearchTitle.text = item.text

        binding.buttonDeleteSearch.setOnClickListener {
            onHistoryClick.onDeleteClick(item
            )
        }
    }

    interface OnHistoryClick{
        fun onDeleteClick(item:HistoryModel)
    }
}