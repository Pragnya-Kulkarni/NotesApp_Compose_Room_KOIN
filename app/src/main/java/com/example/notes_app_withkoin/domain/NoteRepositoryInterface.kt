package com.example.notes_app_withkoin.domain

import com.example.notes_app_withkoin.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepositoryInterface {
    suspend fun addOrEditNote(id: Int, note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNotes(): Flow<List<Note>>
}