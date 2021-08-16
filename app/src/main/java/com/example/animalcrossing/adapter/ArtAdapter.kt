package com.example.animalcrossing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.animalcrossing.R
import com.example.animalcrossing.model.Art

class ArtAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var artList: MutableList<Art> = mutableListOf()

    fun setArtList(artList: MutableList<Art>) {
        this.artList = artList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArtViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArtViewHolder).onBindViewHolder(artList[position].name, artList[position].imageUrl)
    }

    override fun getItemCount(): Int =
        artList.size

    inner class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val preview: ImageView = itemView.findViewById(R.id.preview)
        private val text: TextView = itemView.findViewById(R.id.title)
        fun onBindViewHolder(title: String, url: String) {
            text.text = title
            val option = RequestOptions()
                .override(
                    itemView.resources.getDimension(R.dimen.grid_preview_size).toInt()
                )
            Glide
                .with(itemView.context)
                .load(url)
                .apply(option)
                .into(preview)
        }
    }
}