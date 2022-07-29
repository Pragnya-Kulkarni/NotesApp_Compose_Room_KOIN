package com.example.notes_app_withkoin

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes_app_withkoin.data.model.Note
import com.example.notes_app_withkoin.domain.NoteRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepositoryImpl: NoteRepositoryInterface) : ViewModel() {

    private val _notesList = MutableStateFlow<List<Note>>(emptyList())
    val notesList: StateFlow<List<Note>>
        get() = _notesList

    init {
        getNotes()
    }


    private lateinit var _selectedNote: Note
    val selectedNote: Note
        get() {
            return _selectedNote
        }

    fun setSelectedNote(selectedNote: Note) {
        _selectedNote = selectedNote
    }

    /* private fun getNotes(): List<Note> {
         return listOf(
             Note(
                 1, "Note1",
                 "This is about note one description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 2, "Note2",
                 "This is about note two description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 3, "Note3",
                 "This is about note three description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 4, "Note4",
                 "This is about note four description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 5, "Note5",
                 "This is about note five description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 6, "Note6",
                 "This is about note six description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 7, "Note7",
                 "This is about note seven description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 8, "Note8",
                 "This is about note eight description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 9, "Note9",
                 "This is about note nine description, you can edit it by click on edit button, delete it by clicking on delete button"
             ),
             Note(
                 10, "Note10",
                 "This is about note ten description, you can edit it by click on edit button, delete it by clicking on delete button"
             )
         )
     }*/


    fun addOrUpdateNotes(id: Int, title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepositoryImpl.addOrEditNote(id, Note(title, description))
            getNotes()
        }
    }

    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepositoryImpl.getNotes()
                .catch { e ->
                    //Log error here
                }
                .collect {
                    _notesList.value = it
                }
        }
    }

    fun deleteNotes(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepositoryImpl.deleteNote(note = note)
            getNotes()
        }
    }
}