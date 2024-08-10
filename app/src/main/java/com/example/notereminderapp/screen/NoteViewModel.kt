package com.example.notereminderapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notereminderapp.Respository.NoteRepository
import com.example.notereminderapp.data.Notedatasource
import com.example.notereminderapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val Repository: NoteRepository):ViewModel() {
//    var notelist = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    var notelist= _noteList.asStateFlow()

    init {
       viewModelScope.launch (Dispatchers.IO){
           Repository.getallnotes().distinctUntilChanged().collect{
               listOfNotes->
               if(listOfNotes.isNullOrEmpty()){
                   Log.d("Empty", "Empty: ")
               }else{
                   _noteList.value=listOfNotes
               }
           }
       }
    }

    fun addNote(note: Note)=viewModelScope.launch { Repository.addnote(note) }

    fun removeNote(note: Note) =viewModelScope.launch { Repository.deletenote(note) }
     fun updateNote(note: Note) =viewModelScope.launch { Repository.updatenote(note)}

}
