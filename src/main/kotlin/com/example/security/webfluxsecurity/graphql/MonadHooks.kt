package com.example.security.webfluxsecurity.graphql

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import reactor.core.publisher.Mono
import kotlin.reflect.KType

class MonadHooks : SchemaGeneratorHooks {
    override fun willResolveMonad(type: KType): KType = when (type.classifier) {
        Mono::class -> type.arguments.firstOrNull()?.type
        else -> type
    } ?: type
}