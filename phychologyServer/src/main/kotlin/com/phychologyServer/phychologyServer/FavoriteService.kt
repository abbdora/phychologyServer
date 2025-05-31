package com.phychologyServer.phychologyServer

import com.phychologyServer.phychologyServer.model.books.Book
import com.phychologyServer.phychologyServer.model.books.BookRepository
import com.phychologyServer.phychologyServer.model.user.UserRepository
import org.springframework.stereotype.Service

@Service
class FavoriteService(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
) {
    fun addBookToFavorites(userId: Long, bookId: Long) {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        val book = bookRepository.findById(bookId).orElseThrow { RuntimeException("Book not found") }

        user.favoriteBooks.add(book)
        userRepository.save(user)
    }

    fun getFavoriteBooks(userId: Long): List<Book> {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        println("User favorite books count: ${user.favoriteBooks.size}")
        return user.favoriteBooks.toList()
    }

    fun removeBookFromFavorites(userId: Long, bookId: Long) {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        val book = bookRepository.findById(bookId).orElseThrow { RuntimeException("Book not found") }

        user.favoriteBooks.remove(book)
        userRepository.save(user)
    }
}
