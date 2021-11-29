package com.example.security.webfluxsecurity.graphql

import com.example.security.webfluxsecurity.dto.Book
import com.example.security.webfluxsecurity.service.BookService
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BooksQuery : Query {

    @Autowired
    lateinit var booksService: BookService

    @GraphQLDescription(value = "Get Books")
    fun getAllBooks(): Book {
        booksService.calculate().subscribe{res -> println(res)}
        return Book("foo")
    }
}