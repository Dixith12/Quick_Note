package com.example.notereminderapp.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notereminderapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun CreateScreen(navController: NavController,
                 onAddNewNote:(Note)->Unit) {
    var description by remember{
        mutableStateOf("")
    }
    var title by remember{
        mutableStateOf("")
    }
    var context = LocalContext.current
    Scaffold(topBar = {
        Card(modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
            colors = CardDefaults.cardColors(Color.White)
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxWidth())
            {

                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(26.dp))
                Text("Add Note",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)

                Icon(imageVector = Icons.Default.Check,
                    contentDescription = "Add Note",
                    modifier = Modifier.size(26.dp)
                        .clickable {
                            if(title.isNotEmpty()&&description.isNotEmpty()){
                                //save/add to list
                                onAddNewNote(Note(
                                    title = title, description = description,
                                    time = SimpleDateFormat("EEE MMM yyyy hh:mm a", Locale.getDefault())
                                        .format(Date())
                                ))
                                Toast.makeText(context,"Note Added", Toast.LENGTH_SHORT).show()
                                title=""
                                description=""
                            }
                        })

            }
        }
    }){ innerPadding->

        Column(modifier = Modifier.fillMaxSize()
            .padding(innerPadding))
        {
            OutlinedTextField(value = title,
                onValueChange = {
                    title=it
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 50.dp,start = 8.dp,end = 8.dp),
                placeholder = {
                    Text("Enter Title...",
                        fontWeight = FontWeight.Bold)
                },
                maxLines = 2,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
            OutlinedTextField(value = description,
                onValueChange = {
                    description=it
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp,start = 8.dp,end = 8.dp)
                    .height(400.dp),
                placeholder = {
                    Text("Enter Note...",
                        fontWeight = FontWeight.Bold)
                },
                maxLines = 10,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }

    }
}
