package com.example.security.webfluxsecurity.graphql

import com.expediagroup.graphql.generator.execution.GraphQLContext
import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContext
import com.nimbusds.jwt.JWTParser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

/**
 * Simple [GraphQLContext] that holds extra value and the [ServerRequest]
 */
class MyGraphQLContext(
    request: ServerRequest,
    myCustomValue: String
) : SpringGraphQLContext(request) {

    init {
        val accessTokenStr = request.headers().firstHeader("Authorization")
        val jwtToken = JWTParser.parse(accessTokenStr)
        val grantedAuthorities = jwtToken.jwtClaimsSet.getClaim("Roles").toString().split(",")
            .map { r -> SimpleGrantedAuthority("ROLE_" + r.uppercase().trim()) }.toList()
        val subject = jwtToken.getJWTClaimsSet().getClaim("GivenName")
        ReactiveSecurityContextHolder.withSecurityContext(
            Mono.just(
                SecurityContextImpl(
                    UsernamePasswordAuthenticationToken(subject, jwtToken.getParsedString(), grantedAuthorities))))

    }
}

/**
 * Simple [GraphQLContext] that holds extra value and the [WebSocketSession]
 */
class MySubscriptionGraphQLContext(
    val request: WebSocketSession,
    var auth: String? = null
) : GraphQLContext