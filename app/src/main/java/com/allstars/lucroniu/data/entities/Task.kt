package com.allstars.lucroniu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.allstars.lucroniu.domain.models.TaskModel
import java.util.Date

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id :Long = 0,
    val title:String ,
    val startTime:Date,
    val endTime:Date,
    val priority:Priority,
    val state:State = State.Running
){

}
