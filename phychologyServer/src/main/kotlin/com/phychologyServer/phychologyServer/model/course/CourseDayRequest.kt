package com.phychologyServer.phychologyServer.model.course

data class CourseDayRequest(
    val dayNumber: Int,
    val filePath: String,
    val title: String  // новое поле
)
