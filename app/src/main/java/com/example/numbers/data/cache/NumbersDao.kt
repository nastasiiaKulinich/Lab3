package com.example.numbers.data.cache

import androidx.room.*
import com.example.numbers.data.cache.model.NumberCache

@Dao
interface NumbersDao {

    @Query("SELECT * FROM numbers_table ORDER BY date DESC")
    suspend fun allNumbers(): List<NumberCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(number: NumberCache)

    @Query("SELECT * FROM numbers_table WHERE number = :number")
    suspend fun number(number: String): NumberCache?

}
