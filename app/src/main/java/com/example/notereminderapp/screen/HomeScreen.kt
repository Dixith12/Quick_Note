package com.example.notereminderapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    val sampleNotes = listOf(
        "Buy groceries",
        "Meeting at 3PM in conference room with John and team",
        "Watch movie tonight",
        "Plan weekend trip to the mountains with friends",
        "Read Kotlin docs and practice Jetpack Compose layouts",
        "Call the electrician about fixing the kitchen light"
    )
    Box(modifier = Modifier.fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.background))
    {
        Column(modifier = Modifier.fillMaxSize())
        {
            TopAppBar(title = { Text(text = "Notes") })
            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()) {
                items(sampleNotes){
                    note->
                    NoteCard(note)
                }
            }
        }
    }
}

@Composable
fun NoteCard(note: String) {
    Card()
    {
        Text(text = note)
    }

}
