package com.phychologyServer.phychologyServer.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@RestController
@RequestMapping("/api/images")
class ImageUploadController {

    @Value("\${upload.dir}")
    lateinit var uploadDir: String

    @PostMapping("/upload")
    fun uploadImage(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        return try {
            val filename = UUID.randomUUID().toString() + "_" + file.originalFilename
            val filepath: Path = Paths.get(uploadDir, filename)
            Files.createDirectories(filepath.parent)
            Files.write(filepath, file.bytes)

            val imageUrl = "http://localhost:8080/uploads/$filename"
            ResponseEntity.ok(imageUrl)
        } catch (e: IOException) {
            ResponseEntity.status(500).body("Ошибка загрузки файла")
        }
    }
}
