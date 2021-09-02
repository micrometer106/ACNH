package com.example.animalcrossing.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @SerializedName("event")
    val event: String = "",
    @SerializedName("date")
    val date: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
): Parcelable
