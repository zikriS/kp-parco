package com.example.parcocoon.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcocoon.RVDataclass
import com.example.parcocoon.databinding.ItemListFoodBinding

class rvAdapter(val list: List<RVDataclass>) : RecyclerView.Adapter<rvAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListFoodBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            tvHeading.text = list[position].TitlePram
            tvDesc.text = list[position].DescPram
            titleImage.setImageResource(list[position].imagePram)
        }
    }

    override fun getItemCount(): Int = list.size
}