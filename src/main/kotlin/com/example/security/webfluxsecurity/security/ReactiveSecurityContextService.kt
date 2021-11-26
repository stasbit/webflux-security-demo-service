package com.example.security.webfluxsecurity.security

import com.nimbusds.jwt.JWTParser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.stereotype.Service

@Service
class ReactiveSecurityContextService {
    fun generateSecurityContext(accessTokenStr: String) : SecurityContext {
        val jwtToken = JWTParser.parse(accessTokenStr)
        val grantedAuthorities = jwtToken.jwtClaimsSet.getClaim("Roles").toString().split(",")
            .map { r -> SimpleGrantedAuthority("ROLE_" + r.uppercase()) }.toList()
        val subject = jwtToken.getJWTClaimsSet().getClaim("GivenName")
        return SecurityContextImpl(UsernamePasswordAuthenticationToken(subject, jwtToken.getParsedString(), grantedAuthorities))
    }
}