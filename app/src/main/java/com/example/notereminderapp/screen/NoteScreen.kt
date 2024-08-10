package com.example.notereminderapp.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notereminderapp.component.noteButton
import com.example.notereminderapp.component.noteInputText
import com.example.notereminderapp.data.Notedatasource
import com.example.notereminderapp.model.Note
import com.example.notereminderapp.util.fromDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(modifier: Modifier=Modifier,
    note:List<Note>,
               onAddnote:(Note)-> Unit,
               onRemoveNote:(Note)->Unit){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var context= LocalContext.current
    Column(){
        TopAppBar(title = { Text(text = "Notes", textAlign = TextAlign.Center, fontSize = 27.sp, fontWeight = FontWeight.Bold)}, modifier = modifier.clip(RoundedCornerShape(corner = CornerSize(10.dp))),
            actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription ="notofication", tint = androidx.compose.ui.graphics.Color.Black)
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0x8B07F3A8)))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),horizontalAlignment = Alignment.CenterHorizontally) {

            noteInputText(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                } ){}
            noteInputText(modifier = Modifier.padding(top = 8.dp, bottom = 9.dp),
                text = description,
                label = "Description",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
        } ){}
            noteButton(text = "Save", modifier = Modifier.padding(top = 5.dp, bottom = 1.dp), onClick = {
                if(title.isNotEmpty()&&description.isNotEmpty()){
                    //save/add to list
                    onAddnote(Note(title = title, description = description))
                    Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                    title=""
                    description=""
                }
            })
            HorizontalDivider(modifier = Modifier.padding(10.dp))
                LazyColumn {
                    items(note){
                        note->
                        noteRow(notes = note, onNoteClicked = {
                            onRemoveNote(note)
                            Toast.makeText(context,"Note Removed",Toast.LENGTH_SHORT).show()
                        })
                    }
                }

            }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun noteRow(notes:Note,
            onNoteClicked:(Note)->Unit,
            ){

        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp)), color = Color(0xFFDFE6EB)) {
            Column(modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.Start) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {

                    Text(text = notes.title, fontSize = 17.sp,fontWeight = FontWeight.ExtraBold)
                    Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete Note",modifier = Modifier.clickable {  onNoteClicked.invoke(notes)  }, tint = androidx.compose.ui.graphics.Color.DarkGray)
                }
                Text(text = notes.description,fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = fromDate(notes.entryDate.time), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        } }


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NOtePre(){
    NoteScreen(note=Notedatasource().loadNotes(), onAddnote = {}, onRemoveNote = {})
}