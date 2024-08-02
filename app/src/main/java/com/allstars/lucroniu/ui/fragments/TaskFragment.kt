package com.allstars.lucroniu.ui.fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.allstars.lucroniu.R


import com.allstars.lucroniu.data.database.TaskDatabase
import com.allstars.lucroniu.data.entities.Priority
import com.allstars.lucroniu.data.repository.TaskRepository
import com.allstars.lucroniu.databinding.FragmentLayoutBinding



import com.allstars.lucroniu.domain.models.TaskModel
import com.allstars.lucroniu.ui.LucroniuApp
import com.allstars.lucroniu.ui.adapters.MainTaskListAdapter
import com.allstars.lucroniu.ui.adapters.MainTaskListAdapterCallBack
import com.allstars.lucroniu.ui.utils.AlarmHandler
import com.allstars.lucroniu.ui.utils.PermissionHandler
import com.allstars.lucroniu.ui.viewmodels.TaskFragmentViewModel
import com.allstars.lucroniu.ui.viewmodels.TaskFragmentViewModelFactory
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import kotlin.random.Random

class TaskFragment : Fragment(),MainTaskListAdapterCallBack {
    private lateinit var binding:FragmentLayoutBinding

    private lateinit var viewModel :TaskFragmentViewModel
    private lateinit var mainListLayoutManager:LayoutManager
    lateinit var mainTaskListAdapter: MainTaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLayoutBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Setup navigation for toolbar
        val navController = Navigation.findNavController(binding.mainLayout)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolBar.setupWithNavController(navController,appBarConfiguration)

        // Set up the icon of the user menu item
        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, i ->
            Log.i("Behaviour"," Total Height : ${appBarLayout.layoutParams.height} , Real Height : ${appBarLayout.height} Total Scroll Range : ${appBarLayout.totalScrollRange} Range : $i")
        }
        viewModel = ViewModelProvider(this,TaskFragmentViewModelFactory(TaskRepository((LucroniuApp.taskDatabase as TaskDatabase).taskDao()))).get(TaskFragmentViewModel::class.java)
        mainTaskListAdapter = MainTaskListAdapter(this)
        binding.mainRc.adapter = mainTaskListAdapter



        mainListLayoutManager = LinearLayoutManager(requireContext())
       binding.mainRc.layoutManager = mainListLayoutManager

        //Observe the Flow for the Adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getListOfTaskAsModel().collect{

                    Log.i("TaskService", it.toString())
                    mainTaskListAdapter.submitList(it)

                }
            }
        }
        binding.addButton.setOnClickListener {
            val title = "Random Task"
            val calendar = Calendar.getInstance(TimeZone.getDefault())
            val startTime = calendar.time
            val endTime = Date(startTime.time + Random.nextLong(30000, 100000))
            val priority = Priority.Low
             val taskModel = TaskModel(title = title, startTime = startTime, endTime = endTime, priority = priority)


            //Then call the viewModel insert task for the data base
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PermissionHandler.requestPermission(Manifest.permission.SCHEDULE_EXACT_ALARM,requireActivity()) {}
            }

                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {

                    val insertedId = viewModel.insertTask(taskModel)
                    Log.i("TaskService",insertedId.toString())
                    //Set the alarm here
                    AlarmHandler.setAlarmforTask(taskModel.copy(id=insertedId),requireContext())
                }



        }




    }

    override fun onDeleteButtonClick(task: TaskModel) {
        viewModel.deleteTask(task)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PermissionHandler.requestPermission(Manifest.permission.SCHEDULE_EXACT_ALARM,requireActivity()) {}
        }
        AlarmHandler.cancelAlarmforTask(task,requireContext())
    }


}
//While working with Coordinator Layout sometimes behaviours determine the layout of children