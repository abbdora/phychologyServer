package com.phychologyServer.phychologyServer.model.course

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
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,

    @Column(columnDefinition = "TEXT")
    val descr: String? = null,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(name = "count_days")
    val countDays: Int = 0,

    @ManyToMany(mappedBy = "favoriteCourses")
    @JsonBackReference
    var usersWhoFavorited: MutableSet<User> = mutableSetOf()
)