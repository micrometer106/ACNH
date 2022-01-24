package com.example.animalcrossing.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animalcrossing.data.Fish

@Database(entities = [Fish::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun fishDao(): FishDao

    companion object {
        @Volatile private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "animal-crossing-db").build()
        }
    }
}