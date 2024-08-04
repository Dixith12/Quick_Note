package com.example.notereminderapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.notereminderapp.data.Notedatasource
import com.example.notereminderapp.model.Note

class NoteViewModel:ViewModel() {
    var notelist = mutableStateListOf<Note>()

    init {
        notelist.addAll(Notedatasource().loadNotes())
    }

    fun addNote(note: Note) {
        notelist.add(note)
    }

    fun removeNote(note: Note) {
        notelist.remove(note)
    }
    fun getAllnotes():List<Note>{
        return notelist
    }
}
