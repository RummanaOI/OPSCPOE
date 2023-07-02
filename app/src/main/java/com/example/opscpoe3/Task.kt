package com.example.opscpoe3

import android.media.Image
import java.util.*

data class Task(
    val category: String,
    val name: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val image: String?,
    val date: Date,
)