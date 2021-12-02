package com.example.security.webfluxsecurity.graphql.reactorctx

import com.example.security.webfluxsecurity.security.ReactiveSecurityContextService
import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContext
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.util.context.Context

@Component
class UaaDataFetcherContextEnhancer : DataFetcherContextEnhancer {
    @Autowired
    lateinit var reactiveSecurityContextService: ReactiveSecurityContextService

    override fun getContext(environment: DataFetchingEnvironment): Context {
        val accessTokenStr = environment.getContext<SpringGraphQLContext>().getHTTPRequestHeader("Authorization") ?: ""
        return ReactiveSecurityContextHolder.withSecurityContext(Mono.just(reactiveSecurityContextService.generateSecurityContext(
            accessTokenStr
        )))
    }
}