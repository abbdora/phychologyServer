package com.phychologyServer.phychologyServer.model.course

import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long>