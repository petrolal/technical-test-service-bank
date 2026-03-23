package com.opetrola.technicaltestservicebank.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/auth/**", "/admin/role", "/admin/user").permitAll()
                it.requestMatchers("/admin/**").hasRole("GERENTE")
                it.requestMatchers("/user/**").hasAnyRole("GERENTE", "USER", "CAIXA")
                it.anyRequest().authenticated()
            }
            .httpBasic {  }
            .build()
    }
}