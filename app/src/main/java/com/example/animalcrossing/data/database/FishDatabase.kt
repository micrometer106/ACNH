package com.example.animalcrossing.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animalcrossing.data.Fish

@Database(entities = [Fish::class], version = 1)
abstract class FishDatabase : RoomDatabase() {
    abstract fun fishDao(): FishDao
}