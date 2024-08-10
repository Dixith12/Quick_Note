package com.example.notereminderapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notereminderapp.data.Notedatasource
import com.example.notereminderapp.model.Note
import com.example.notereminderapp.screen.NoteScreen
import com.example.notereminderapp.ui.theme.NoteReminderAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notereminderapp.screen.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteReminderAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var viewModel = viewModel<NoteViewModel>()
                    NoteApp(viewModel)
                    }
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteApp(noteViewModel: NoteViewModel){
    var notelist = noteViewModel.notelist.collectAsState().value

    NoteScreen( note = notelist, onAddnote ={
        noteViewModel.addNote(it)
    }, onRemoveNote = {
        noteViewModel.removeNote(it)
    })
}

