package com.phychologyServer.phychologyServer.model.courseDay

import com.phychologyServer.phychologyServer.model.course.Course
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "course_day")
data class CourseDay(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    val course: Course,

    @Column(name = "day_number")
    val dayNumber: Int,

    @Column(name = "file_path")
    val filePath: String,

    @Column(name = "title")
    val title: String
)

