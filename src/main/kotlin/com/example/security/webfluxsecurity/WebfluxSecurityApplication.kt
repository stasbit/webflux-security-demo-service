package com.example.security.webfluxsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class WebfluxSecurityApplication

fun main(args: Array<String>) {
	runApplication<WebfluxSecurityApplication>(*args)
}
