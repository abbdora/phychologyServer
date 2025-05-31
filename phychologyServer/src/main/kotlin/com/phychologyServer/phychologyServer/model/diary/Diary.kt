package com.phychologyServer.phychologyServer.model.diary

import com.fasterxml.jackson.annotation.JsonBackReference
import com.phychologyServer.phychologyServer.model.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "diary")
data class Diary(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    @JsonBackReference
    val user: User,

    @Column(nullable = false)
    val emotion: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)

