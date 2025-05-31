package com.phychologyServer.phychologyServer.controller

import com.phychologyServer.phychologyServer.model.course.Course
import com.phychologyServer.phychologyServer.model.course.CourseDayRequest
import com.phychologyServer.phychologyServer.model.course.CourseRepository
import com.phychologyServer.phychologyServer.model.courseDay.CourseDay
import com.phychologyServer.phychologyServer.model.courseDay.CourseDayRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = ["*"])
class CourseController(
    private val courseRepository: CourseRepository,
    private val courseDayRepository: CourseDayRepository
) {

    @GetMapping
    fun getAllCourses(): List<Course> = courseRepository.findAll()

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<Course> =
        courseRepository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun addCourse(@RequestBody course: Course): Course = courseRepository.save(course)

    // Получить все дни курса по id курса
    @GetMapping("/{courseId}/days")
    fun getCourseDays(@PathVariable courseId: Long): List<CourseDay> =
        courseDayRepository.findByCourse_IdOrderByDayNumber(courseId)

    // Добавить день курса
    @PostMapping("/{courseId}/days")
    fun addCourseDay(
        @PathVariable courseId: Long,
        @RequestBody courseDayRequest: CourseDayRequest
    ): ResponseEntity<CourseDay> {
        val course = courseRepository.findById(courseId).orElse(null) ?: return ResponseEntity.notFound().build()
        val courseDay = CourseDay(
            course = course,
            dayNumber = courseDayRequest.dayNumber,
            filePath = courseDayRequest.filePath,
            title = courseDayRequest.title  // добавляем сюда
        )
        val saved = courseDayRepository.save(courseDay)
        return ResponseEntity.ok(saved)
    }

}