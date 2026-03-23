package com.opetrola.technicaltestservicebank.security

import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.repository.UserJpaRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService (private val userJpaRepository: UserJpaRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userJpaRepository.findByCpf(username)
            ?: throw UsernameNotFoundException("User not found: $username")

        return User(
            user.cpf,
            user.password,
            listOf()
        )
    }

}