package com.example.security.webfluxsecurity.controller

import com.example.security.webfluxsecurity.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class BooksController {

    @Autowired
    lateinit var booksService: BookService

    @GetMapping("/books/all")
    @ResponseBody
    fun getAllBooks(): Mono<String> {
        return booksService.calculate()
    }
}