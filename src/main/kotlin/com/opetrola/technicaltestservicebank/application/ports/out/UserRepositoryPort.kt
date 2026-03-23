package com.opetrola.technicaltestservicebank.application.ports.out

import com.opetrola.technicaltestservicebank.domain.model.User

interface UserRepositoryPort {
    fun findByCpf(cpf: String): User?
    fun save(user: User): User
    fun findAll(): List<User>
}