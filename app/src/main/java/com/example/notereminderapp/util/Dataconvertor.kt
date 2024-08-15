package com.example.notereminderapp.util

import androidx.room.TypeConverter
import java.util.Date

class Dataconvertor {
    @TypeConverter
    fun timestamptodate(date:Date):Long{
        return date.time
    }

    @TypeConverter
    fun datetotimestamp(timestamp:Long):Date?{
        return Date(timestamp)
    }
}