package com.phychologyServer.phychologyServer.controller

import com.phychologyServer.phychologyServer.FavoriteCourseService
import com.phychologyServer.phychologyServer.model.course.Course
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/favorite-courses")
class FavoriteCourseController(
    private val favoriteCourseService: FavoriteCourseService
) {
    @PostMapping("/add")
    fun addCourseToFavorites(@RequestParam userId: Long, @RequestParam courseId: Long): ResponseEntity<String> {
        favoriteCourseService.addCourseToFavorites(userId, courseId)
        return ResponseEntity.ok("Course added to favorites")
    }

    @GetMapping("/{userId}")
    fun getFavorites(@PathVariable userId: Long): ResponseEntity<List<Course>> {
        return ResponseEntity.ok(favoriteCourseService.getFavoriteCourses(userId))
    }

    @DeleteMapping("/delete")
    fun deleteCourseFromFavorites(@RequestParam userId: Long, @RequestParam courseId: Long): ResponseEntity<String> {
        favoriteCourseService.removeCourseFromFavorites(userId, courseId)
        return ResponseEntity.ok("Course removed from favorites")
    }
}
