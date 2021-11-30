package com.example.security.webfluxsecurity.graphql

import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContextFactory
import com.nimbusds.jwt.JWTParser
import org.springframework.http.server.ServerHttpRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

@Component
class CustomGraphQLContextFactory: SpringGraphQLContextFactory<MyGraphQLContext>() {
    override suspend fun generateContext(request: ServerRequest): MyGraphQLContext = MyGraphQLContext(
        request = request,
        myCustomValue = request.headers().firstHeader("Authorization") ?: "defaultContext"
    )
}

// : GraphQLContextFactory<MyGraphQLContext, ServerHttpRequest> {

//    override suspend fun generateContext(request: ServerHttpRequest): MyGraphQLContext? {
//
//    }
// }