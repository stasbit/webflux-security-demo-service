package com.example.security.webfluxsecurity.graphql

import com.example.security.webfluxsecurity.dto.Book
import com.example.security.webfluxsecurity.service.BookService
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import com.expediagroup.graphql.server.operations.Query
import com.nimbusds.jwt.JWTParser
import graphql.GraphQLContext
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.stereotype.Component
import reactor.core.publisher.Hooks
import reactor.core.publisher.Mono

@Component
class BooksQuery : Query {

    @Autowired
    lateinit var booksService: BookService

    @GraphQLDescription(value = "Get Books")
    suspend fun getAllBooks(): Book {
        println("1")

//        val accessTokenStr = ctx.getHTTPRequestHeader("Authorization")
//        val jwtToken = JWTParser.parse(accessTokenStr)
//        val grantedAuthorities = jwtToken.jwtClaimsSet.getClaim("Roles").toString().split(",")
//            .map { r -> SimpleGrantedAuthority("ROLE_" + r.uppercase().trim()) }.toList()
//        val subject = jwtToken.getJWTClaimsSet().getClaim("GivenName")
//        ReactiveSecurityContextHolder.withSecurityContext(
//            Mono.just(
//                SecurityContextImpl(
//                    UsernamePasswordAuthenticationToken(subject, jwtToken.getParsedString(), grantedAuthorities)
//                )
//            ))

//        dfe.
//            ReactiveSecurityContextHolder
//                .getContext()
//                .map { context -> context.getAuthentication() }
//                .subscribe { auth -> println("BooksQuery security ctx in ReactiveSecurityContextRepository = " + auth.name) }
//        }
        Hooks.enableContextLossTracking();
        booksService.calculate().subscribe { res -> println(res) }
        return Book("foo")
    }
}