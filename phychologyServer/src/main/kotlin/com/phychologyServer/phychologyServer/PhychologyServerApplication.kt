package com.phychologyServer.phychologyServer

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class PhychologyServerApplication

fun main(args: Array<String>) {
	runApplication<PhychologyServerApplication>(*args)
}
