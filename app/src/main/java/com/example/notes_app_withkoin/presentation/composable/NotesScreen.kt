package com.example.notes_app_withkoin.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notes_app_withkoin.data.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect


@Composable
fun NotesScreen(
    navController: NavHostController,
    lstNotes: StateFlow<List<Note>>,
    selectNote: (Note) -> Unit,
    deleteNote: (Note) -> Unit
) {

    val notes = lstNotes.collectAsState().value
    val colourList = listOf(
        Color.Yellow.copy(alpha = 0.05f),
        Color.Green.copy(alpha = 0.05F),
        Color.Magenta.copy(alpha = 0.05F),
        Color.Cyan.copy(alpha = 0.05F)
    )
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectNote(Note( "", ""))
                navController.navigate("add")
            }) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = notes,
                /**
                 * Use key param to define unique keys representing the items in a mutable list,
                 * instead of using the default key (list position). This prevents unnecessary
                 * recompositions.
                 */
                key = { note -> note.id as Any }
            ) { note ->
                val backgroundColor = colourList[note.id as Int % 4]
                DetailCard(
                    note = note,
                    navController = navController,
                    selectNote,
                    backgroundColor,
                    deleteNote
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DetailCard(
    note: Note,
    navController: NavHostController,
    selectNote: (Note) -> Unit,
    colour: Color,
    deleteNote: (Note) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        elevation = 5.dp,
        backgroundColor = colour
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement =
            Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(text = note.title)
                Text(text = note.description)
            }
            IconButton(
                modifier = Modifier.weight(0.3f),
                onClick = {
                    selectNote(note)
                    navController.navigate("edit")
                }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
            IconButton(
                modifier = Modifier.weight(0.3f),
                onClick = { deleteNote(note) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete"
                )
            }
        }

    }

}