package com.example.animalcrossing.model

import com.google.gson.annotations.SerializedName

data class Availability(
        @SerializedName("months")
        val months: String = "",
        @SerializedName("time")
        val time: String = "",
)
