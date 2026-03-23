package com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.repository

import com.opetrola.technicaltestservicebank.application.ports.out.UserRepositoryPort
import com.opetrola.technicaltestservicebank.domain.model.User
import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity.UserEntity
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    private val userJpaRepository: UserJpaRepository
): UserRepositoryPort {
    override fun findByCpf(cpf: String): User? {

        if (cpf.isEmpty()) {
            throw RuntimeException("User cpf cannot be empty")
        }

        val entity = userJpaRepository.findByCpf(cpf) ?: return null

        return User(
            name = entity.name,
            password = entity.password,
            cpf = entity.cpf,
            active = entity.active,
            roles = entity.roles,
        )
    }

    override fun save(user: User): User {
        val entity = userJpaRepository.save(
            UserEntity(
                name = user.name,
                cpf = user.cpf,
                password = user.password,
                active = true,
                roles = user.roles,
            )
        )

        return User(
            name = entity.name,
            cpf = entity.cpf,
            password = entity.password,
            active = false,
            roles = entity.roles,
        )
    }

    override fun findAll(): List<User> {
        return userJpaRepository.findAll().map { entity -> User(
            id = entity.id,
            name = entity.name,
            cpf = entity.cpf,
            password = entity.password,
            active = entity.active,
            roles = entity.roles
        )}
    }
}