package com.opetrola.technicaltestservicebank.security

import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwkService: JwkService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val header = request.getHeader("Authorization")

        if (header != null && header.startsWith("Bearer ")) {
            val token = header.substring(7)

            val claims = Jwts.parserBuilder()
                .setSigningKey(jwkService.key)
                .build()
                .parseClaimsJws(token)
                .body

            val cpf = claims.subject

            // 👇 AQUI SIM entra roles
            val roles = claims["roles"] as List<*>

            val authorities = roles.map {
                SimpleGrantedAuthority("ROLE_$it")
            }

            val auth = UsernamePasswordAuthenticationToken(
                cpf,
                null,
                authorities
            )

            SecurityContextHolder.getContext().authentication = auth
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.servletPath

        return path.startsWith("/swagger-ui") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-ui.html")
    }
}