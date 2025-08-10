package com.example.notereminderapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notereminderapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class MNote(val title:String,
                 val info:String,
                 val time:String=SimpleDateFormat("EEE MMM yyyy hh:mm a", Locale.getDefault())
                     .format(Date()))
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(note:List<Note>) {
    var search by remember {
        mutableStateOf("")
    }

    Scaffold (
        topBar = {
        TopAppBar(title =
            {
                Text(text = "Quick_Note",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp))
            },
            actions = {
                Icon(imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Thme",
                    tint= Color.Black,
                    modifier = Modifier.padding(end=20.dp))
            })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {

        },
            containerColor = Color.Black) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White)
        }}
        ){innerPadding->
        Surface(modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
            color = MaterialTheme.colorScheme.background){
            Column(modifier = Modifier
                .fillMaxSize()){

                OutlinedTextField(value = search,
                    onValueChange = {
                        search=it
                    },
                    modifier = Modifier.fillMaxWidth(0.95f)
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    placeholder = {
                        Text("Search Note...")
                    },
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = "search",
                            tint = Color.Black)
                    }
                )
                LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                        .padding(horizontal = 7.dp)) {
                    itemsIndexed(note){index,note->
                        val backgroundColor = if (index % 2 == 0) {
                            Color(0xD342EF24)
                        } else {
                            Color(0xFF38D2D2)
                        }
                        NoteCard(note, backgroundColor)
                    }

                }

            }

        }

    }
}

@Composable
fun NoteCard(note: Note, backgroundColor: Color) {
    Card(modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(backgroundColor))
    {
        Column(modifier = Modifier.fillMaxSize()
            .padding(horizontal = 2.dp, vertical = 5.dp))
        {
            Text(text = note.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = Color.White)
            Text(text = note.description,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(10.dp),
                color = Color.White)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = note.time,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(10.dp),
                color = Color.White)
        }
    }

}
