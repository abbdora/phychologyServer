package com.phychologyServer.phychologyServer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity  // ⚠️ Важно: включает настройку безопасности
class SecurityConfig {

    // Bean для шифрования паролей (оставляем)
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    // ⚠️ Новый Bean: настройка доступа к endpoints
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    // Разрешаем без авторизации:
                    .requestMatchers("/api/auth/**").permitAll()    // Регистрация/логин
                    .requestMatchers("/api/books/**").permitAll()   // Книги
                    .requestMatchers("/api/images/**").permitAll()  // Загрузка изображений
                    .requestMatchers("/uploads/**").permitAll()     // Статические файлы
                    .requestMatchers("/api/courses/**").permitAll()
                    .requestMatchers("/texts/**").permitAll() // Курсы и всё, что связано с ними
                    .requestMatchers("/api/favorites/**").permitAll()
                    .requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()                    // Всё остальное — только для авторизованных
            }
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers("/uploads/**")
        }
    }

}
