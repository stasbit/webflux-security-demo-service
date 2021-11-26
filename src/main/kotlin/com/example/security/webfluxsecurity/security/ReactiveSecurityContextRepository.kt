package com.example.security.webfluxsecurity.security

import org.springframework.http.HttpCookie
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Service
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Service
class ReactiveSecurityContextRepository(private val reactiveSecurityContextService: ReactiveSecurityContextService) : ServerSecurityContextRepository {

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        return Mono.empty()
    }

    override fun load(exchange: ServerWebExchange?): Mono<SecurityContext> {
       //generate token http://jwtbuilder.jamiekurtz.com/
        val accessToken: MutableList<HttpCookie>? = exchange?.request?.cookies?.get("access_token")
        if (accessToken?.first()?.value != null) {
            return Mono.just(reactiveSecurityContextService.generateSecurityContext(accessToken.first().value))
        }
        return Mono.empty()
    }
}