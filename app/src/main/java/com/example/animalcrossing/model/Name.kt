package com.example.animalcrossing.model

import com.google.gson.annotations.SerializedName

data class Name(
        @SerializedName("name-USen")
        val USen: String = "",
        @SerializedName("name-EUen")
        val EUen: String = "",
        @SerializedName("name-EUde")
        val EUde: String = "",
        @SerializedName("name-EUes")
        val EUes: String = "",
        @SerializedName("name-USes")
        val USes: String = "",
        @SerializedName("name-EUfr")
        val EUfr: String = "",
        @SerializedName("name-USfr")
        val USfr: String = "",
        @SerializedName("name-EUit")
        val EUit: String = "",
        @SerializedName("name-EUnl")
        val EUnl: String = "",
        @SerializedName("name-CNzh")
        val CNzh: String = "",
        @SerializedName("name-TWzh")
        val TWzh: String = "",
        @SerializedName("name-JPja")
        val JPja: String = "",
        @SerializedName("name-KRko")
        val KRko: String = "",
        @SerializedName("name-EUru")
        val EUru: String = ""
)
