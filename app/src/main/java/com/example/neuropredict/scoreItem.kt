package com.example.neuropredict

data class scoreItem(
    val CorrectTap: Int,
    val IncorrectTap: Int,
    val Offset_distance: Double,
    val Tap_no: Int,
    val _id: String,
    val median_inter_tap_1: Double,
    val median_inter_tap_2: Double
)