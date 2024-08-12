package com.allstars.lucroniu.ui.adapters

import android.animation.LayoutTransition
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RotateDrawable
import android.graphics.drawable.ShapeDrawable
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionListenerAdapter
import androidx.transition.TransitionManager
import com.allstars.lucroniu.data.entities.Task
import com.allstars.lucroniu.databinding.MainlistitemBinding
import com.allstars.lucroniu.domain.models.TaskModel
import java.util.Date
import kotlin.math.roundToInt


interface MainTaskListAdapterCallBack{
    fun onDeleteButtonClick(task: TaskModel)


}
class MainTaskListAdapter (val callBack: MainTaskListAdapterCallBack): ListAdapter<TaskModel,MainTaskListAdapter.MainTaskListViewHolder>(TaskItemDiffUtil) {

    inner class MainTaskListViewHolder( val binding:MainlistitemBinding):ViewHolder(binding.root){
          var countDowntimer:CountDownTimer? = null
        fun bind(task: TaskModel){
            binding.titleTv.text= task.title
            //We need to calculate the progress of the task from the start time to the current time
            //First get the current time
            val calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault())
            val currentTime = calendar.time
            //Then calculate the percentage
            val percentofTimeSinceStart = (currentTime.time-task.startTime.time)/100
            //Then set the progress of the progress bar to it

            binding.timeProgressBar.progress = percentofTimeSinceStart.toInt()
            Log.i("TaskService",binding.timeProgressBar.progress.toString())
            val futureTime = task.endTime.time -  currentTime.time
            val totalTime = (task.endTime.time - task.startTime.time).toDouble()
            countDowntimer = CustomCountDownTimer(futureTime,1000,{
                 //First we calculate the total time

                 Log.i("TaskService",totalTime.toString())
                 //Then we find the  percent of 1 second to total time
                 var percent1SecondtoTotalTime:Double = (it /totalTime)*100
                 Log.i("TaskService",percent1SecondtoTotalTime.toString())
                 //Increment the progress bar by that
                 var currentprogress = binding.timeProgressBar.progress
                 binding.timeProgressBar. progress = (100 - percent1SecondtoTotalTime).roundToInt()
                 Log.i("TaskService",binding.timeProgressBar.progress.toString())
             }){
                //First we set the progress to 100
                binding.timeProgressBar.progress = 100
                //Then we set the 2nd drawable in the layer drawable gradient type to linear
              val layeredProgressDrawable =   binding.timeProgressBar.progressDrawable as LayerDrawable
                 val secondDrawable = layeredProgressDrawable.getDrawable(1) as RotateDrawable
                //We then get the wrapped drawable  as Gradient Drawable
                (secondDrawable.drawable as GradientDrawable).gradientType = GradientDrawable.LINEAR_GRADIENT


             }
            binding.buttonGroup.visibility = View.GONE

            countDowntimer?.start()
            binding.root.setOnClickListener {

            }
            binding.root.setOnLongClickListener {
                //Testing Transistions for layouts
                TransitionManager.beginDelayedTransition(binding.mainConstraintLayout, Slide())
                //The transistions have listners that we can use to know whetehr
                if(binding.buttonGroup.visibility == View.VISIBLE){
                    binding.buttonGroup.visibility = View.GONE
                }else{
                    binding.buttonGroup.visibility = View.VISIBLE
                }

                true
            }
            binding.deleteButton.setOnClickListener {
                callBack.onDeleteButtonClick(task)
                countDowntimer?.cancel()
            }

        }
    }

    object TaskItemDiffUtil:DiffUtil.ItemCallback<TaskModel>(){
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return  oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTaskListViewHolder {
        val binding = MainlistitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MainTaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainTaskListViewHolder, position: Int) {
           holder.bind(getItem(position))
    }

    override fun onViewAttachedToWindow(holder: MainTaskListViewHolder) {
        super.onViewAttachedToWindow(holder)

    }


    override fun onViewDetachedFromWindow(holder: MainTaskListViewHolder) {
        super.onViewDetachedFromWindow(holder)

    }


}
class CustomCountDownTimer(val endTime : Long, val interval:Long , val onCustomTick:(Long)->Unit,  val onFinish:()->Unit):CountDownTimer(endTime,interval){
    override fun onTick(millisUntilFinished: Long) {
        onCustomTick(millisUntilFinished)
    }
    override fun onFinish() {
        onFinish
    }
}