package com.allstars.lucroniu.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.allstars.lucroniu.data.entities.Priority
import com.allstars.lucroniu.data.entities.Task
import com.allstars.lucroniu.data.repository.TaskRepository
import com.allstars.lucroniu.domain.models.TaskModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import kotlin.random.Random

class TaskFragmentViewModel( private val taskRepository: TaskRepository) : ViewModel() {


 suspend fun getListOfTaskAsModel(): Flow<List<TaskModel>> {
    return taskRepository.getAllTask() .map {it.map{
        TaskModel(it.id,it.title,it.startTime,it.endTime,it.priority)
    } }
}
    suspend fun insertTask(taskModel: TaskModel ) :Long  =
        taskRepository.insertTask(taskModel.toTask())








    fun deleteTask(taskModel: TaskModel){
        val task = taskModel.toTask()
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }

    }

}
class TaskFragmentViewModelFactory(  val taskRepository: TaskRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TaskFragmentViewModel::class.java))
            TaskFragmentViewModel(taskRepository = taskRepository) as T
        else super.create(modelClass)
    }
}