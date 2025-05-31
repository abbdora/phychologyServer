package com.phychologyServer.phychologyServer.controller

import com.phychologyServer.phychologyServer.model.user.LoginRequest
import com.phychologyServer.phychologyServer.model.user.RegisterRequest
import com.phychologyServer.phychologyServer.model.user.User
import com.phychologyServer.phychologyServer.model.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["*"])
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    private val logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<String> {
        logger.info("Register request: $request")
        if (userRepository.existsByUsername(request.username)) {
            return ResponseEntity.badRequest().body("Имя пользователя уже занято")
        }
        if (userRepository.existsByEmail(request.email)) {
            return ResponseEntity.badRequest().body("Email уже зарегистрирован")
        }

        val user = User(
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password)
        )
        userRepository.save(user)
        return ResponseEntity.ok("Регистрация прошла успешно")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Map<String, Any>> {
        logger.info("Login request: $request")
        val user = userRepository.findByUsername(request.username)
            ?: return ResponseEntity.status(401).body(mapOf(
                "success" to false,
                "message" to "Неверное имя пользователя или пароль"
            ))

        return if (passwordEncoder.matches(request.password, user.password)) {
            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Успешный вход",
                "user_id" to user.id,
                "username" to user.username
            ))
        } else {
            ResponseEntity.status(401).body(mapOf(
                "success" to false,
                "message" to "Неверное имя пользователя или пароль"
            ))
        }
    }
}
