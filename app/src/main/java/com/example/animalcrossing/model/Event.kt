package com.example.animalcrossing.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("event")
    val event: String = "",
    @SerializedName("date")
    val date: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
)
