package com.example.animalcrossing.data.database

import androidx.room.*
import com.example.animalcrossing.data.Fish
import kotlinx.coroutines.flow.Flow

@Dao
interface FishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(fishList: List<Fish>)

    @Query("SELECT * FROM fish")
    fun getAll(): Flow<List<Fish>>
}