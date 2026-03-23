package com.opetrola.technicaltestservicebank.application.usecase

import com.opetrola.technicaltestservicebank.application.ports.out.UserRepositoryPort
import com.opetrola.technicaltestservicebank.security.JwkService
import org.springframework.security.crypto.password.PasswordEncoder

class AuthenticationUseCase(
    private val repository: UserRepositoryPort,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwkService
) {
    fun execute(cpf: String, password: String): String {
        val user = repository.findByCpf(cpf)
            ?: throw RuntimeException("User $cpf doesn't exist")

        if (!passwordEncoder.matches(password, user.password)) {
            throw RuntimeException("User ${user.name} doesn't match password format")
        }

        return jwtService.generateToken(user)
    }
}