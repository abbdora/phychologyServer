package com.phychologyServer.phychologyServer.controller

import com.phychologyServer.phychologyServer.model.books.Book
import com.phychologyServer.phychologyServer.model.books.BookRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = ["*"])
class BookController(private val repository: BookRepository) {

    @GetMapping
    fun x(): List<Book> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<Book> =
        repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun addBook(@RequestBody book: Book): Book = repository.save(book)


    @GetMapping("/search")  // Убрали дублирующий /api/books
    fun searchBooks(@RequestParam query: String): List<Book> {
        return repository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query)
    }

}
