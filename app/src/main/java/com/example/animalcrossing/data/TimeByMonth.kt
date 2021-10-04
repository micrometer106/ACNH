package com.example.animalcrossing.data

import com.google.gson.annotations.SerializedName

data class TimeByMonth(
        @SerializedName("1")
        val jan: String = "",
        @SerializedName("2")
        val feb: String = "",
        @SerializedName("3")
        val mar: String = "",
        @SerializedName("4")
        val apr: String = "",
        @SerializedName("5")
        val may: String = "",
        @SerializedName("6")
        val jun: String = "",
        @SerializedName("7")
        val jul: String = "",
        @SerializedName("8")
        val aug: String = "",
        @SerializedName("9")
        val sep: String = "",
        @SerializedName("10")
        val oct: String = "",
        @SerializedName("11")
        val nov: String = "",
        @SerializedName("12")
        val dec: String = ""
)
