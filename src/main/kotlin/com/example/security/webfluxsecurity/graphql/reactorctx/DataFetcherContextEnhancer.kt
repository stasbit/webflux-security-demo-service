package com.example.security.webfluxsecurity.graphql.reactorctx

import graphql.schema.DataFetchingEnvironment
import reactor.util.context.Context

interface DataFetcherContextEnhancer {

    /**
     * This method extracts a graphqlContext and creates reactor context
     */
    fun getContext(environment: DataFetchingEnvironment): Context

}