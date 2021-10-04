package com.example.animalcrossing.data

import com.google.gson.annotations.SerializedName

data class Hemisphere(
        @SerializedName("availability_array")
        val availability: MutableList<Availability> = mutableListOf(),
        @SerializedName("times_by_month")
        val timesByMonth: TimeByMonth = TimeByMonth(),
        @SerializedName("months")
        val months: String = "",
        @SerializedName("months_array")
        val monthsArray: MutableList<Int> = mutableListOf()
)
