package com.example.security.webfluxsecurity.service

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BookService {

    @PreAuthorize("hasAuthority('ROLE_USER')")
    fun calculate() : Mono<String> = Mono.just("The method BookService.calculate called")
}