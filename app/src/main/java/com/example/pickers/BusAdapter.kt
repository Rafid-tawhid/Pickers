package com.example.pickers

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pickers.databinding.SchedulesRowBinding
import com.example.pickers.helpers.BusSchedule


class BusAdapter(val callBack:(BusSchedule)->Unit) :ListAdapter<BusSchedule,BusAdapter.MovieViewHoldder>(MovieDiffUtil()) {

    class MovieViewHoldder(val binding:SchedulesRowBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(schedule:BusSchedule){
                binding.schedule=schedule
            }

        }



    class MovieDiffUtil:DiffUtil.ItemCallback<BusSchedule>(){
        override fun areItemsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {

            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHoldder {
        val binding=SchedulesRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return MovieViewHoldder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHoldder, position: Int) {
        val movie=getItem(position)
        holder.bind(movie)

        holder.itemView.setOnClickListener {
            callBack(movie)
        }

//        holder.binding.imageView2.setOnClickListener {
//            callBack(movie)
//        }

    }
}