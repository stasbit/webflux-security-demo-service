package com.example.security.webfluxsecurity.graphql.reactorctx

import com.expediagroup.graphql.generator.execution.KotlinDataFetcherFactoryProvider
import graphql.schema.DataFetcherFactory
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

class MyDataFetcherFactory : KotlinDataFetcherFactoryProvider {
    override fun functionDataFetcherFactory(target: Any?, kFunction: KFunction<*>): DataFetcherFactory<Any?> {
        TODO("Not yet implemented")
    }

    override fun propertyDataFetcherFactory(kClass: KClass<*>, kProperty: KProperty<*>): DataFetcherFactory<Any?> {
        TODO("Not yet implemented")
    }
}