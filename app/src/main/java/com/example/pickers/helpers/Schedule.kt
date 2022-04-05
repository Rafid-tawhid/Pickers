package com.example.pickers.helpers



val cityList= listOf("Dhaka","Comilla","Borishal","Noakhali","Khulna","Cox's Bazar","Sylhet","Rajshahi")

val scheduleList= mutableListOf<BusSchedule>()

val busTypeEconomy="Economy"
val busTypeBusiness="Business"

data class BusSchedule(
    val id:Long,
    val busName:String,
    val from:String,
    val to:String,
    val depatureDate:String,
    val depatureTime:String,
    val busType:String
)
