package com.allstars.lucroniu.data.repository


import com.allstars.lucroniu.data.dao.TaskDao

import com.allstars.lucroniu.data.database.TaskDatabase
import com.allstars.lucroniu.data.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TaskRepository( private val dao: TaskDao) {

   suspend  fun getAllTask(): Flow<List<Task>> {
      return withContext(Dispatchers.IO) {
           dao.getAllTasks()
       }
    }
    suspend fun insertTask(task: Task) :Long {
        return withContext(Dispatchers.IO){
            dao.insertTask(task)
        }

    }
    suspend fun updateTask(task: Task){
        return withContext(Dispatchers.IO){
             dao.updateTask(task)
        }

    }

    suspend fun deleteTask(task: Task){
        return withContext(Dispatchers.IO){
            dao.deleteTask(task)
        }
    }

}