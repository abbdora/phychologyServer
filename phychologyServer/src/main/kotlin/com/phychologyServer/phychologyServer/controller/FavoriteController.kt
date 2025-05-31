package com.phychologyServer.phychologyServer.controller

import com.phychologyServer.phychologyServer.FavoriteService
import com.phychologyServer.phychologyServer.model.books.Book
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/favorites")
class FavoriteController(
    private val favoriteService: FavoriteService
) {
    @PostMapping("/add")
    fun addBookToFavorites(@RequestParam userId: Long, @RequestParam bookId: Long): ResponseEntity<String> {
        favoriteService.addBookToFavorites(userId, bookId)
        return ResponseEntity.ok("Book added to favorites")
    }

    @GetMapping("/{userId}")
    fun getFavorites(@PathVariable userId: Long): ResponseEntity<List<Book>> {
        return ResponseEntity.ok(favoriteService.getFavoriteBooks(userId))
    }

    @DeleteMapping("/delete")
    fun deleteBookFromFavorites(
        @RequestParam userId: Long,
        @RequestParam bookId: Long
    ): ResponseEntity<String> {
        favoriteService.removeBookFromFavorites(userId, bookId)
        return ResponseEntity.ok("Book removed from favorites")
    }
}
