package com.example.security.webfluxsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import reactor.core.publisher.Hooks
import reactor.tools.agent.ReactorDebugAgent

@SpringBootApplication
@EnableAspectJAutoProxy
class WebfluxSecurityApplication

fun main(args: Array<String>) {
	ReactorDebugAgent.init();
	Hooks.onOperatorDebug();
	runApplication<WebfluxSecurityApplication>(*args)
}
