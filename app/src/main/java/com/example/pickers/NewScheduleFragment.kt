package com.example.pickers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pickers.databinding.FragmentNewScheduleBinding
import com.example.pickers.dialogs.DatePickerFragment
import com.example.pickers.dialogs.TimePickerFragment
import com.example.pickers.helpers.BusSchedule
import com.example.pickers.helpers.busTypeEconomy
import com.example.pickers.helpers.cityList
import com.example.pickers.helpers.scheduleList

class NewScheduleFragment : Fragment() {
    private var from="Dhaka"
    private var to="Dhaka"
    private var busType= busTypeEconomy
    private var selectedDate=""
    private var selectedTime=""
    private lateinit var binding:FragmentNewScheduleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNewScheduleBinding.inflate(inflater,container,false)

       initSpinner()
        initBusTypeRadioGroup()
        binding.dateBtn.setOnClickListener {

            DatePickerFragment{
                selectedDate=it
                binding.showDateTv.text=it
            }.show(childFragmentManager,null)
        }
        binding.timeBtn.setOnClickListener {
            TimePickerFragment{
                selectedTime=it
                binding.showTimeTv.text=it
            }.show(childFragmentManager,null)
        }

        binding.saveBtn.setOnClickListener { saveInfo() }


        return binding.root
    }



    private fun saveInfo() {
        val busName=binding.busNameId.text.toString()
        if(from==to){
            Toast.makeText(activity, "Origin and destination Can't be same", Toast.LENGTH_SHORT).show()

            return
        }
        val schedule=BusSchedule(
            id=System.currentTimeMillis(),
            busName = busName,
            from=from,
            to=to,
            depatureDate = selectedDate,
            depatureTime = selectedTime,
            busType=busType
        )
        Log.d("INFO", "saveInfo: $schedule")
        scheduleList.add(schedule)
        findNavController().navigate(R.id.action_newScheduleFragment_to_scheduleFragment)
    }

    private fun initBusTypeRadioGroup() {

        binding.busTypeRg.setOnCheckedChangeListener { radioGroup, i ->
            val rb:RadioButton=radioGroup.findViewById(i)
            busType=rb.text.toString()
        }
    }

    private fun initSpinner() {
        val adpter=ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1,
            cityList)
        binding.citySpinnerFromId.adapter=adpter
        binding.citySpinnerToId.adapter=adpter
        binding.citySpinnerFromId.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                from=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.citySpinnerToId.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                to=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }


}