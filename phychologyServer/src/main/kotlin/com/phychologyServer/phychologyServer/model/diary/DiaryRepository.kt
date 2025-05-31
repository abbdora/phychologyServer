package com.phychologyServer.phychologyServer.model.diary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface DiaryRepository : JpaRepository<Diary, Long> {
    fun findByUserId(userId: Long): List<Diary>
    fun findByUserIdAndCreatedAtBetween(userId: Long, start: LocalDateTime, end: LocalDateTime): List<Diary>
}
