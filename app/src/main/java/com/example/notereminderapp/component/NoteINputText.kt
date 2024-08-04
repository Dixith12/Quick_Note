package com.example.notereminderapp.component

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun noteInputText(modifier: Modifier=Modifier,
                  text:String,
                  label:String,
                  maxLine:Int=1,
                  onTextChange:(String)->Unit,
                  onImeAction:()->Unit){
    var keyboardController= LocalSoftwareKeyboardController.current
    TextField(value = text ,
        onValueChange =onTextChange,
        label = { Text(text = label, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)},
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
        keyboardActions = KeyboardActions(onDone ={
            onImeAction()
            keyboardController?.hide()
        }),
        maxLines = maxLine,
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Black,cursorColor = Color.Black),
    modifier=modifier)
}

@Composable
fun noteButton(modifier: Modifier=Modifier,
               text:String,
               enabled:Boolean=true,
               onClick:()->Unit){
    Button(onClick = { onClick.invoke() }, modifier = modifier, shape = CircleShape, colors = ButtonDefaults.buttonColors(containerColor = Color(0x8B07F3A8), contentColor = Color.Black), enabled = enabled)
    {
        Text(text = text, fontSize = 18.sp)
    }
}