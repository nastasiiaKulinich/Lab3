package com.example.numbers.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers_table")
data class NumberCache(
    @PrimaryKey @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "fact") val fact: String,
    @ColumnInfo(name = "date") val date: Long
)