package com.example.notes_app_withkoin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes_app_withkoin.presentation.composable.AddEditNote
import com.example.notes_app_withkoin.presentation.composable.NotesScreen
import com.example.notes_app_withkoin.ui.theme.Notes_App_WithKoinTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text("Notes App") },
                        backgroundColor = MaterialTheme.colors.primary
                    )
                },
            ) {
                val notesViewModel = getViewModel<NoteViewModel>()
               // val notesViewModel = ViewModelProvider(this,).get(NoteViewModel::class.java)
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        NotesScreen(
                            navController = navController,
                            notesViewModel.notesList,
                            notesViewModel::setSelectedNote,
                            notesViewModel::deleteNotes
                        )
                    }
                    composable("edit") {
                        AddEditNote(
                            navController = navController,
                            notesViewModel::addOrUpdateNotes,
                            notesViewModel.selectedNote,
                            true
                        )
                    }
                    composable("add") {
                        AddEditNote(
                            navController = navController,
                            notesViewModel::addOrUpdateNotes,
                            notesViewModel.selectedNote,
                            false
                        )
                    }
                }

            }
        }
    }
}
