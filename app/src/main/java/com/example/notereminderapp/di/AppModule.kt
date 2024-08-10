package com.example.notereminderapp.di

import android.content.Context
import androidx.room.Room
import com.example.notereminderapp.data.NoteDatabase
import com.example.notereminderapp.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(noteDatabase: NoteDatabase):NoteDatabaseDao
    = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabse(@ApplicationContext context: Context):NoteDatabase
    = Room.databaseBuilder(context,NoteDatabase::class.java,"note_db").fallbackToDestructiveMigration().build()


}