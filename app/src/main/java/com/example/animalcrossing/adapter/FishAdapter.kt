package com.example.animalcrossing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcrossing.R
import com.example.animalcrossing.model.Fish

class FishAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var fishList: MutableList<Fish> = mutableListOf()

    fun setFishList(fishList: MutableList<Fish>) {
        this.fishList = fishList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FishViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fish_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FishViewHolder).onBindViewHolder(fishList[position].name)
    }

    override fun getItemCount(): Int =
        fishList.size

    inner class FishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.title)
        fun onBindViewHolder(title: String) {
            text.text = title
        }
    }
}