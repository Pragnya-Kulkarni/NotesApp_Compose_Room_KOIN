package com.example.notes_app_withkoin.data.datasource

import com.example.notes_app_withkoin.data.model.Note
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val noteDao: NoteDao) : LocalDataSourceInterface {
    override suspend fun addOrEditNote(id: Int, note: Note) {
        if (id == -1)
            noteDao.insertNote(note)
        else {
            val noteToEdit: Note? = noteDao.getNoteById(id)
            noteToEdit?.let {
                it.title = note.title
                it.description = note.description
                noteDao.updateNote(noteToEdit)
            }
        }
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }
}

interface LocalDataSourceInterface {
    suspend fun addOrEditNote(id: Int, note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNotes(): Flow<List<Note>>
}