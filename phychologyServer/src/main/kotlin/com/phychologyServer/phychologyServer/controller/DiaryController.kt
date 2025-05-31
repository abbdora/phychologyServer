package com.phychologyServer.phychologyServer.controller

import com.phychologyServer.phychologyServer.model.diary.Diary
import com.phychologyServer.phychologyServer.model.diary.DiaryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/diary")
class DiaryController(
    private val diaryService: DiaryService
) {

    @PostMapping
    fun addEmotion(
        @RequestParam userId: Long,
        @RequestParam emotion: String
    ): ResponseEntity<Diary> {
        val diary = diaryService.createEmotion(userId, emotion)
        return ResponseEntity.ok(diary)
    }

    @GetMapping("/{userId}")
    fun getAllEmotions(@PathVariable userId: Long): ResponseEntity<List<Diary>> {
        return ResponseEntity.ok(diaryService.getEmotionsByUser(userId))
    }

    @GetMapping("/{userId}/by-date")
    fun getEmotionsByDate(
        @PathVariable userId: Long,
        @RequestParam date: String // формат: "2025-03-27"
    ): ResponseEntity<List<Diary>> {
        val parsedDate = LocalDate.parse(date)
        return ResponseEntity.ok(diaryService.getEmotionsByUserAndDate(userId, parsedDate))
    }

    @DeleteMapping("/{id}")
    fun deleteEmotion(@PathVariable id: Long): ResponseEntity<Void> {
        diaryService.deleteEmotion(id)
        return ResponseEntity.noContent().build()
    }
}
