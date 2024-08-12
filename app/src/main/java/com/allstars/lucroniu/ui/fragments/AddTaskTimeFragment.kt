package com.allstars.lucroniu.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.allstars.lucroniu.databinding.AddTaskFragmentTimeSectionLayoutBinding
import com.allstars.lucroniu.databinding.AddTaskTitleAndDescriptionSectionBinding
import com.allstars.lucroniu.ui.adapters.AddTimeSelectorAdapter

class AddTaskTimeFragment : Fragment() {
    lateinit var binding : AddTaskFragmentTimeSectionLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddTaskFragmentTimeSectionLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(binding.root)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)

        binding.mainRc.apply {
            adapter = AddTimeSelectorAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = object : RecyclerView.ItemAnimator() {
                override fun animateDisappearance(
                    viewHolder: RecyclerView.ViewHolder,
                    preLayoutInfo: ItemHolderInfo,
                    postLayoutInfo: ItemHolderInfo?
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun animateAppearance(
                    viewHolder: RecyclerView.ViewHolder,
                    preLayoutInfo: ItemHolderInfo?,
                    postLayoutInfo: ItemHolderInfo
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun animatePersistence(
                    viewHolder: RecyclerView.ViewHolder,
                    preLayoutInfo: ItemHolderInfo,
                    postLayoutInfo: ItemHolderInfo
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun animateChange(
                    oldHolder: RecyclerView.ViewHolder,
                    newHolder: RecyclerView.ViewHolder,
                    preLayoutInfo: ItemHolderInfo,
                    postLayoutInfo: ItemHolderInfo
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun runPendingAnimations() {
                    TODO("Not yet implemented")
                }

                override fun endAnimation(item: RecyclerView.ViewHolder) {
                    TODO("Not yet implemented")
                }

                override fun endAnimations() {
                    TODO("Not yet implemented")
                }

                override fun isRunning(): Boolean {
                    TODO("Not yet implemented")
                }

            }
        }
        binding.addTime.apply {
            setOnClickListener{
                ( binding.mainRc.adapter as AddTimeSelectorAdapter).addTimeValue(requireContext())
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}