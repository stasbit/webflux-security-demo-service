package com.example.security.webfluxsecurity.graphql

import com.expediagroup.graphql.generator.execution.FunctionDataFetcher
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetchingEnvironment
import reactor.core.publisher.Mono
import kotlin.reflect.KFunction as KFunction1

class CustomFunctionDataFetcher(target: Any?, fn: KFunction1<*>, objectMapper: ObjectMapper) : FunctionDataFetcher(target, fn, objectMapper) {
    override fun get(environment: DataFetchingEnvironment): Any? = when (val result = super.get(environment)) {
        is Mono<*> -> result.toFuture()
        else -> result
    }
}