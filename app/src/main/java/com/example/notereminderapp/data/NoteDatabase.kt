package com.example.notereminderapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.notereminderapp.model.Note
import com.example.notereminderapp.util.Dataconvertor
import com.example.notereminderapp.util.UUIDconvertor

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Dataconvertor::class, UUIDconvertor::class)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun noteDao():NoteDatabaseDao
}