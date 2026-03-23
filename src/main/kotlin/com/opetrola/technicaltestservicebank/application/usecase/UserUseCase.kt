package com.opetrola.technicaltestservicebank.application.usecase

import com.opetrola.technicaltestservicebank.application.ports.out.UserRepositoryPort
import com.opetrola.technicaltestservicebank.domain.model.User

class UserUseCase(
    private val userRepositoryPort: UserRepositoryPort
) {
    fun findAll(): List<User> {
        return userRepositoryPort.findAll().map {
            User(
                id = it.id,
                name = it.name,
                cpf = it.cpf,
                password = it.password,
                active = it.active,
                roles = it.roles
            )
        }
    }
}