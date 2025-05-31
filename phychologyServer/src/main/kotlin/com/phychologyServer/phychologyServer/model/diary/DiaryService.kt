package com.phychologyServer.phychologyServer.model.diary

import com.phychologyServer.phychologyServer.model.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class DiaryService(
    private val diaryRepository: DiaryRepository,
    private val userRepository: UserRepository
) {
    fun createEmotion(userId: Long, emotion: String): Diary {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        return diaryRepository.save(Diary(user = user, emotion = emotion))
    }

    fun getEmotionsByUser(userId: Long): List<Diary> {
        return diaryRepository.findByUserId(userId)
    }

    fun getEmotionsByUserAndDate(userId: Long, date: LocalDate): List<Diary> {
        val start = date.atStartOfDay()
        val end = date.plusDays(1).atStartOfDay()
        return diaryRepository.findByUserIdAndCreatedAtBetween(userId, start, end)
    }

    fun deleteEmotion(id: Long) {
        diaryRepository.deleteById(id)
    }
}
