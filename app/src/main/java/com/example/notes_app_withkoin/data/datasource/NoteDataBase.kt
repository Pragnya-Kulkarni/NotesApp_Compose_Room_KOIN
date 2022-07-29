package com.example.notes_app_withkoin.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes_app_withkoin.data.model.Note

@Database
    (entities = [Note::class], version = 1, exportSchema = false)

abstract class NoteDataBase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}