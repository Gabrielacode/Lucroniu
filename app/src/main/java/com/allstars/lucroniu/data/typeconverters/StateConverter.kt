package com.allstars.lucroniu.data.typeconverters

import androidx.room.TypeConverter
import com.allstars.lucroniu.data.entities.State

class StateConverter {

    @TypeConverter
    fun fromStateToInt(state: State):Int{
        return state.ordinal
    }

    @TypeConverter
    fun fromIntToState(int:Int):State{
        return State.entries[int]
    }
}