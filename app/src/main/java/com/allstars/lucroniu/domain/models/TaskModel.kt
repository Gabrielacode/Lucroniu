package com.allstars.lucroniu.domain.models

import com.allstars.lucroniu.data.entities.Priority
import com.allstars.lucroniu.data.entities.State
import com.allstars.lucroniu.data.entities.Task
import java.util.Date

data class TaskModel(
    val id :Long = 0,
    val title:String ,
    val startTime:Date,
    val endTime:Date,
    val priority:Priority,
    val state: State = State.Running
){
public fun toTask():Task{
    return Task(this.id,this.title,this.startTime,this.endTime,this.priority,this.state)
}
}
