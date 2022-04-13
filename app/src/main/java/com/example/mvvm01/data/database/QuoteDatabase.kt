package com.example.mvvm01.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm01.data.database.dao.QuoteDao
import com.example.mvvm01.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun getQuotesDao(): QuoteDao
}