package com.allstars.lucroniu.data.typeconverters

import androidx.room.TypeConverter
import com.allstars.lucroniu.data.entities.Priority

class PriorityConverter {
    @TypeConverter
    fun fromPrioritytoInt(priority: Priority):Int{
        return priority.ordinal
    }

    @TypeConverter
    fun fromInttoPriority(int:Int):Priority{
        return Priority.entries[int]
    }
}