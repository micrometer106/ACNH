package com.example.animalcrossing.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalcrossing.data.Fish

@Dao
interface FishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(fish: Fish)

    @Query("SELECT * FROM fish")
    fun getAll(): MutableList<Fish>
}