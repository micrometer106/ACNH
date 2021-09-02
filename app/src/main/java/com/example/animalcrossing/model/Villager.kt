package com.example.animalcrossing.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Villager(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("alt_name")
    val altName: String = "",
    @SerializedName("title_color")
    val titleColor: String = "",
    @SerializedName("text_color")
    val textColor: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("species")
    val species: String = "",
    @SerializedName("personality")
    val personality: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("birthday_month")
    val birthdayMonth: String = "",
    @SerializedName("birthday_day")
    val birthdayDay: String = "",
    @SerializedName("sign")
    val sign: String = "",
    @SerializedName("quote")
    val quote: String = "",
    @SerializedName("phrase")
    val phrase: String = "",
    @SerializedName("clothing")
    val clothing: String = "",
    @SerializedName("islander")
    val islander: String = "",
    @SerializedName("debut")
    val debut: String = "",
    @SerializedName("prev_phrases")
    val prevPhrases: MutableList<String> = mutableListOf(),
    @SerializedName("appearances")
    val appearances: MutableList<String> = mutableListOf()
): Parcelable
