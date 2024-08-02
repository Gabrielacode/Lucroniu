package com.allstars.lucroniu.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.allstars.lucroniu.data.dao.TaskDao
import com.allstars.lucroniu.data.entities.Task
import com.allstars.lucroniu.data.typeconverters.DateConverter
import com.allstars.lucroniu.data.typeconverters.PriorityConverter
import com.allstars.lucroniu.data.typeconverters.StateConverter

@Database(
    entities = [Task::class],
    version = 1,
)
@TypeConverters(DateConverter::class,PriorityConverter::class,StateConverter::class)
 abstract class TaskDatabase:RoomDatabase() {
     abstract fun taskDao() :TaskDao

     companion object{
         private var database : RoomDatabase? = null

         fun getDatabase(context : Context):RoomDatabase{
             if (database == null){
                  database = Room.databaseBuilder(context, TaskDatabase::class.java,"TaskDatabase").build()
                 return database!!
             }else{
             return database!!
             }

         }
     }
}