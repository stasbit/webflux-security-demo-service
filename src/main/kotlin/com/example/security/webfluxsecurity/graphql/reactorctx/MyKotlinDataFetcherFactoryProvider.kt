package com.example.security.webfluxsecurity.graphql.reactorctx

import com.expediagroup.graphql.generator.execution.SimpleKotlinDataFetcherFactoryProvider
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcherFactory
import org.springframework.context.ApplicationContext
import kotlin.reflect.KFunction

class MyKotlinDataFetcherFactoryProvider(
    private val objectMapper: ObjectMapper,
    private val applicationContext: ApplicationContext,
    private val uaaDataFetcherContextEnhancer: UaaDataFetcherContextEnhancer
) : SimpleKotlinDataFetcherFactoryProvider(objectMapper) {
    override fun functionDataFetcherFactory(target: Any?, kFunction: KFunction<*>): DataFetcherFactory<Any?> =
        DataFetcherFactory { MyDataFetcher(target, kFunction, objectMapper, applicationContext, uaaDataFetcherContextEnhancer) }
}
