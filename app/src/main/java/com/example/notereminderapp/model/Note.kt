package com.example.notereminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale
import java.util.UUID

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey
    val id:UUID= UUID.randomUUID(),
     @ColumnInfo(name = "note_title")
    val title:String,

                @ColumnInfo(name = "note_description")
    val description:String,
    @ColumnInfo(name="time")
    val time:String= SimpleDateFormat("EEE MMM yyyy hh:mm a", Locale.getDefault())
        .format(Date())

    //@ColumnInfo(name="note_entry_date")
    //val entryDate:Date=Date.from(Instant.now())
)
