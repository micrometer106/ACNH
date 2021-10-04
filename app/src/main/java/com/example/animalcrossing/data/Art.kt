package com.example.animalcrossing.data

import com.google.gson.annotations.SerializedName

data class Art(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("has_fake")
    val hasFake: Boolean = false,
    @SerializedName("fake_image_url")
    val fakeImageUrl: String = "",
    @SerializedName("art_name")
    val artName: String = "",
    @SerializedName("author")
    val author: String = "",
    @SerializedName("year")
    val year: String = "",
    @SerializedName("art_style")
    val artStyle: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("buy")
    val buy: Int = 0,
    @SerializedName("sell")
    val sell: Int = 0,
    @SerializedName("availability")
    val availability: String = "",
    @SerializedName("authenticity")
    val authenticity: String = "",
    @SerializedName("width")
    val width: Float = 0.0f,
    @SerializedName("length")
    val length: Float = 0.0f
)
