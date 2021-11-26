package com.example.security.webfluxsecurity.config

import com.example.security.webfluxsecurity.security.ReactiveSecurityContextRepository
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
        reactiveSecurityContextRepository: ReactiveSecurityContextRepository
    ): SecurityWebFilterChain? {
        // Disable all non required filters
        http.httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .cors().disable()
            .logout().disable()

        http
            //.addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange()
            .pathMatchers("/login", "/logout", "/api/sse-service/events").permitAll()
            .anyExchange().authenticated()

        http.securityContextRepository(reactiveSecurityContextRepository)
        return http.build()
    }
}