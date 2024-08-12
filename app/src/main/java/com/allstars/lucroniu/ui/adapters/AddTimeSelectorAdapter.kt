package com.allstars.lucroniu.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.allstars.lucroniu.databinding.AddTimeItemListBinding
import java.util.Stack

class AddTimeSelectorAdapter() :RecyclerView.Adapter<AddTimeSelectorAdapter.TimeViewHolder>() {
    val stackofTimeValues = Stack<TimeValue>()
    init {
     stackofTimeValues.add(TimeValue.Second())
    }
    inner class TimeViewHolder( val binding: AddTimeItemListBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(value :TimeValue){
         binding.valueOfTime.setText(value.currentValue.toString())
           binding.timeplaceholder.text = value.faceName
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val binding = AddTimeItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
         return stackofTimeValues.count()
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bind(stackofTimeValues[position])
    }
    fun addTimeValue(context: Context) {
        try {
             when(stackofTimeValues.last()){
                is TimeValue.Second ->stackofTimeValues.push(TimeValue.Minute())
                is TimeValue.Minute -> stackofTimeValues.push(TimeValue.Hour())
                is TimeValue.Hour -> stackofTimeValues.push(TimeValue.Day())
                is TimeValue.Day -> stackofTimeValues.push(TimeValue.Week())
                is TimeValue.Week -> stackofTimeValues.push(TimeValue.Month())
                is TimeValue.Month -> stackofTimeValues.push(TimeValue.Year())
                is TimeValue.Year ->Toast.makeText(context,"Can't add another element",Toast.LENGTH_SHORT).show()
            }
        }catch (e :NoSuchElementException){
            stackofTimeValues.push(TimeValue.Second())
        }

    }
}
sealed class TimeValue(val maxValue:Int,var currentValue:Int =0, val faceName:String){
    class Second():TimeValue(60, faceName = "SECOND"){

    }
    class Minute():TimeValue(60, faceName = "MINUTE"){

    }
    class Hour():TimeValue(24,faceName = "HOUR"){

    }
    class Day():TimeValue(31,faceName = "DAY"){

    }
    class Week():TimeValue(4,faceName = "WEEK"){
    }
    class Month():TimeValue(12,faceName = "MONTH")


    class Year ():TimeValue(1000,faceName = "YEAR"){}
}