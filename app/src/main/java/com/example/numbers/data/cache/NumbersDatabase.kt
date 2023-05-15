package com.example.numbers.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.numbers.data.cache.model.NumberCache

@Database(entities = [NumberCache::class], version = 1, exportSchema = false)
abstract class NumbersDatabase : RoomDatabase() {

    abstract fun numbersDao(): NumbersDao

    companion object {
        @Volatile
        private var INSTANCE: NumbersDatabase? = null

        fun getDatabase(context: Context): NumbersDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NumbersDatabase::class.java,
                    "numbers_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
