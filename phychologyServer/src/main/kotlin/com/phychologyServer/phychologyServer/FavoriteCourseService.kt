package com.phychologyServer.phychologyServer

import com.phychologyServer.phychologyServer.model.course.Course
import com.phychologyServer.phychologyServer.model.course.CourseRepository
import com.phychologyServer.phychologyServer.model.user.UserRepository
import org.springframework.stereotype.Service

@Service
class FavoriteCourseService(
    private val userRepository: UserRepository,
    private val courseRepository: CourseRepository
) {
    fun addCourseToFavorites(userId: Long, courseId: Long) {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        val course = courseRepository.findById(courseId).orElseThrow { RuntimeException("Course not found") }

        user.favoriteCourses.add(course)
        userRepository.save(user)
    }

    fun getFavoriteCourses(userId: Long): List<Course> {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        return user.favoriteCourses.toList()
    }

    fun removeCourseFromFavorites(userId: Long, courseId: Long) {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        val course = courseRepository.findById(courseId).orElseThrow { RuntimeException("Course not found") }

        user.favoriteCourses.remove(course)
        userRepository.save(user)
    }
}
