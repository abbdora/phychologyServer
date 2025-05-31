package com.phychologyServer.phychologyServer.model.books

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    fun findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(title: String, author: String): List<Book>
}
