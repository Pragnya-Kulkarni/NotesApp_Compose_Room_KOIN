package com.example.notes_app_withkoin.data.datasource

import androidx.room.*
import com.example.notes_app_withkoin.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note where id= :id")
    suspend fun getNoteById(id:Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)

    @Update
    suspend fun updateNote(note:Note)

}