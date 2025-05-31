package com.phychologyServer.phychologyServer

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:uploads/")  // Путь к папке с изображениями

        registry.addResourceHandler("/texts/**")
            .addResourceLocations("classpath:/static/texts/")
    }
}

