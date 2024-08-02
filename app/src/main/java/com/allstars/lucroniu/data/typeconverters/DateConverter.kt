package com.allstars.lucroniu.data.typeconverters

import androidx.room.TypeConverter
import java.util.Date


class DateConverter {

    @TypeConverter
    public fun fromDatetoLong(date : Date):Long {

         return date.time
    }

    @TypeConverter
    public fun fromLongtoDate(value:Long):Date{
        return Date(value)
    }
}