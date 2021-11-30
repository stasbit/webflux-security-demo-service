package com.example.security.webfluxsecurity.graphql

import com.expediagroup.graphql.generator.execution.SimpleKotlinDataFetcherFactoryProvider
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcherFactory
import kotlin.reflect.KFunction

class CustomDataFetcherFactoryProvider(
    private val objectMapper: ObjectMapper
) : SimpleKotlinDataFetcherFactoryProvider(objectMapper) {

    override fun functionDataFetcherFactory(target: Any?, kFunction: KFunction<*>): DataFetcherFactory<Any?> = DataFetcherFactory<Any?> {
        CustomFunctionDataFetcher(
            target = target,
            fn = kFunction,
            objectMapper = objectMapper)
    }
}