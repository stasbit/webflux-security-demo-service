package com.example.security.webfluxsecurity.graphql.reactorctx

import com.expediagroup.graphql.server.spring.execution.SpringDataFetcher
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetchingEnvironment
import kotlinx.coroutines.reactor.asCoroutineContext
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KFunction
import kotlin.reflect.full.instanceParameter

class MyDataFetcher(
    private val target: Any?,
    private val fn: KFunction<*>,
    objectMapper: ObjectMapper,
    context: ApplicationContext,
    private val dataFetcherContextEnhancer: DataFetcherContextEnhancer
) : SpringDataFetcher(target, fn, objectMapper, context) {

    override fun get(environment: DataFetchingEnvironment): Any? {
        val instance: Any? = target ?: environment.getSource<Any?>()
        val instanceParameter = fn.instanceParameter

        return if (instance != null && instanceParameter != null) {
            val parameterValues = getParameters(fn, environment)
                .plus(instanceParameter to instance)

            if (fn.isSuspend) {
                val coroutineContext = getCoroutineContext(environment)
                runSuspendingFunction(parameterValues, coroutineContext)
            } else {
                runBlockingFunction(parameterValues)
            }
        } else {
            null
        }
    }

    protected fun getCoroutineContext(environment: DataFetchingEnvironment): CoroutineContext =
        dataFetcherContextEnhancer
            ?.getContext(environment)
            ?.asCoroutineContext() ?: EmptyCoroutineContext
}
