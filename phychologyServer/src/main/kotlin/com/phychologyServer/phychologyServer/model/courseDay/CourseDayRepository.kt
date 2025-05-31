package com.phychologyServer.phychologyServer.model.courseDay

import org.springframework.data.jpa.repository.JpaRepository

interface CourseDayRepository : JpaRepository<CourseDay, Long> {
    fun findByCourse_IdOrderByDayNumber(courseId: Long): List<CourseDay>
}