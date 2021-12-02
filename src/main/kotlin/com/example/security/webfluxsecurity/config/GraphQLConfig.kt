package com.example.security.webfluxsecurity.config

import com.example.security.webfluxsecurity.graphql.reactorctx.MyKotlinDataFetcherFactoryProvider
import com.example.security.webfluxsecurity.graphql.reactorctx.UaaDataFetcherContextEnhancer
import com.expediagroup.graphql.generator.execution.KotlinDataFetcherFactoryProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class GraphQLConfig {

    @Bean
    @Primary
    fun kotlinDataFetcherProvider(
        objectMapper: ObjectMapper,
        applicationContext: ApplicationContext,
        uaaDataFetcherContextEnhancer: UaaDataFetcherContextEnhancer
    ): KotlinDataFetcherFactoryProvider {
        return MyKotlinDataFetcherFactoryProvider(objectMapper, applicationContext, uaaDataFetcherContextEnhancer)
    }
}