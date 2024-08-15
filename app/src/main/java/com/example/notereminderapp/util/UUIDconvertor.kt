package com.example.notereminderapp.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDconvertor {
    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

    @TypeConverter
    fun toUUID(uuidString: String?): UUID? {
        return UUID.fromString(uuidString)
    }

}