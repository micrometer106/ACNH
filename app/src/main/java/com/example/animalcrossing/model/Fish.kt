package com.example.animalcrossing.model

import com.google.gson.annotations.SerializedName

data class Fish (
        @SerializedName("url")
        val url: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("number")
        val number: Int = -1,
        @SerializedName("image_url")
        val imageUrl: String = "",
        @SerializedName("render_url")
        val renderUrl: String = "",
        @SerializedName("time")
        val time: String = "",
        @SerializedName("location")
        val location: String = "",
        @SerializedName("shadow_size")
        val shadowSize: String = "",
        @SerializedName("rarity")
        val rarity: String = "",
        @SerializedName("total_catch")
        val totalCatch: Int = -1,
        @SerializedName("sell_nook")
        val sellNook: Int = -1,
        @SerializedName("sell_cj")
        val sellCJ: Int = -1,
        @SerializedName("tank_width")
        val tankWidth: Int = -1,
        @SerializedName("tank_length")
        val tankLength: Int = -1,
        @SerializedName("catchphrases")
        val catchphrases: MutableList<String> = mutableListOf(),
        @SerializedName("north")
        val north: Hemisphere = Hemisphere(),
        @SerializedName("south")
        val south: Hemisphere = Hemisphere()
)
