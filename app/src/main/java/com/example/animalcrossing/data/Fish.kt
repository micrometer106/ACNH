package com.example.animalcrossing.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fish")
data class Fish (
        @ColumnInfo(name = "url") @SerializedName("url")
        val url: String = "",
        @ColumnInfo(name = "name") @SerializedName("name")
        val name: String = "",
        @PrimaryKey @ColumnInfo(name = "number") @SerializedName("number")
        val number: Int = -1,
        @ColumnInfo(name = "imageUrl") @SerializedName("image_url")
        val imageUrl: String = "",
        @ColumnInfo(name = "renderUrl") @SerializedName("render_url")
        val renderUrl: String = "",
        @ColumnInfo(name = "time") @SerializedName("time")
        val time: String = "",
        @ColumnInfo(name = "location") @SerializedName("location")
        val location: String = "",
        @ColumnInfo(name = "shadowSize") @SerializedName("shadow_size")
        val shadowSize: String = "",
        @ColumnInfo(name = "rarity") @SerializedName("rarity")
        val rarity: String = "",
        @ColumnInfo(name = "totalCatch") @SerializedName("total_catch")
        val totalCatch: Int = -1,
        @ColumnInfo(name = "sellNook") @SerializedName("sell_nook")
        val sellNook: Int = -1,
        @ColumnInfo(name = "sellCJ") @SerializedName("sell_cj")
        val sellCJ: Int = -1,
        @ColumnInfo(name = "tankWidth") @SerializedName("tank_width")
        val tankWidth: Int = -1,
        @ColumnInfo(name = "tankLength") @SerializedName("tank_length")
        val tankLength: Int = -1,
//        @ColumnInfo(name = "catchphrases") @SerializedName("catchphrases")
//        val catchphrases: MutableList<String> = mutableListOf(),
//        @ColumnInfo(name = "north") @SerializedName("north")
//        val north: Hemisphere = Hemisphere(),
//        @ColumnInfo(name = "south") @SerializedName("south")
//        val south: Hemisphere = Hemisphere()
)
