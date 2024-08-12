package com.allstars.lucroniu.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.allstars.lucroniu.R
import com.allstars.lucroniu.databinding.AddTaskTitleAndDescriptionSectionBinding

class AddTaskTitleandDescriptionFragment : Fragment(R.layout.add_task_title_and_description_section) {
    lateinit var binding :AddTaskTitleAndDescriptionSectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddTaskTitleAndDescriptionSectionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(binding.root)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}