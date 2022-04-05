package com.example.pickers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pickers.databinding.FragmentScheduleBinding
import com.example.pickers.helpers.BusSchedule
import com.example.pickers.helpers.scheduleList


class ScheduleFragment : Fragment() {

    private lateinit var binding:FragmentScheduleBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentScheduleBinding.inflate(inflater,container,false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleFragment_to_newScheduleFragment)
        }
        val adapter=BusAdapter(::onReceiveMovieFromAdapter)

        binding.rv.layoutManager= LinearLayoutManager(activity)
        binding.rv.adapter=adapter
//        adapter.submitList(employeeList)
        adapter.submitList(scheduleList)


        return binding.root

    }
    private fun onReceiveMovieFromAdapter(movie: BusSchedule){

        Toast.makeText(activity, ""+movie.busName, Toast.LENGTH_SHORT).show()
    }

}