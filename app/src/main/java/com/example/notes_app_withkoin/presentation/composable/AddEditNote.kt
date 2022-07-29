package com.example.notes_app_withkoin.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notes_app_withkoin.data.model.Note


@Composable
fun AddEditNote(
    navController: NavHostController,
    addOrUpdateNotes: (Int, String, String) -> Unit,
    selectedNote: Note,
    isEdit: Boolean
) {

    var title by rememberSaveable { mutableStateOf(selectedNote.title) }
    var description by rememberSaveable {
        mutableStateOf(selectedNote.description)
    }
   Surface() {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(20.dp),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           TextField(
               modifier = Modifier.fillMaxWidth(),
               value = title,
               onValueChange = { newText ->
                   title = newText
               },
               maxLines = 2
           )
           TextField(
               modifier = Modifier.fillMaxWidth(),
               value = description,
               onValueChange = { newText ->
                   description = newText
               },
               maxLines = 15
           )
           Button(
               modifier = Modifier.fillMaxWidth().padding(5.dp)
                  ,
               onClick = {
                   if (isEdit) {
                       selectedNote.title = title
                       selectedNote.description = description
                       selectedNote.id?.let {
                           addOrUpdateNotes(
                               it,
                               selectedNote.title,
                               selectedNote.description
                           )
                       }
                       navController.popBackStack()
                   } else {
                       selectedNote.title = title
                       selectedNote.description = description
                       addOrUpdateNotes(-1, selectedNote.title, selectedNote.description)
                       navController.popBackStack()
                   }
               }
           ) {
               Text(text = "Save", textAlign = TextAlign.Center)
           }
       }
   }
}
