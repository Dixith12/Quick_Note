package com.example.notereminderapp.Respository

import com.example.notereminderapp.data.NoteDatabaseDao
import com.example.notereminderapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addnote(note:Note)=noteDatabaseDao.insert(note)
    suspend fun updatenote(note: Note)=noteDatabaseDao.update(note)
    suspend fun deletenote(note: Note)=noteDatabaseDao.deleteNote(note)
    suspend fun getallnotes():Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}