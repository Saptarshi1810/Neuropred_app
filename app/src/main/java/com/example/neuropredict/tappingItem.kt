package com.example.neuropredict

data class tappingItem(
    val correct_tap: Int,
    val incorrect_tap: Int,
    val median_inter_tap_1: Double,
    val median_inter_tap_2: Double,
    val offset_distance: Double,
    val patient_id: Int,
    val tap_no: Int
)