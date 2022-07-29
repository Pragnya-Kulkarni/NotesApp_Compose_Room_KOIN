package com.example.notes_app_withkoin.data.repo

import com.example.notes_app_withkoin.data.datasource.LocalDataSourceInterface
import com.example.notes_app_withkoin.data.model.Note
import com.example.notes_app_withkoin.domain.NoteRepositoryInterface
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDataSource: LocalDataSourceInterface) :
    NoteRepositoryInterface {
    override suspend fun addOrEditNote(id: Int, note: Note) {
        noteDataSource.addOrEditNote(id, note = note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDataSource.deleteNote(note)
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return noteDataSource.getNotes()
    }
}