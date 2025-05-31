package com.phychologyServer.phychologyServer.model.books

import com.fasterxml.jackson.annotation.JsonBackReference
import com.phychologyServer.phychologyServer.model.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table


@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,
    val author: String? = null,
    val category: String? = null,

    @Column(name = "short_desc", columnDefinition = "TEXT")
    val shortDesc: String? = null,

    @Column(name = "full_text", columnDefinition = "LONGTEXT")
    val fullText: String? = null,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(name = "likes_count")
    val likesCount: Int = 0,

    @ManyToMany(mappedBy = "favoriteBooks")
    @JsonBackReference
    var usersWhoFavorited: MutableSet<User> = mutableSetOf()
)


