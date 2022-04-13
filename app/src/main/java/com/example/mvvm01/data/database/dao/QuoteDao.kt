package com.example.mvvm01.data.database.dao

import androidx.room.*
import com.example.mvvm01.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {

    @Query(value = "Select * from quote_table order by author desc")
    suspend fun getAllQuotes():List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quotes:List<QuoteEntity>)

    @Query(value="Delete from quote_table")
    suspend fun deleteAllQuotes()
}