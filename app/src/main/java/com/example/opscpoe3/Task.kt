package com.example.opscpoe3

import android.media.Image
import java.util.*

data class Task(
    val category: String,
    val name: String,
    val description: String,
    val date: String,
    val hours: Int,
    val imageUrl: String?
)