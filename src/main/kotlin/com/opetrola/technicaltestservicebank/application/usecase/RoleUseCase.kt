package com.opetrola.technicaltestservicebank.application.usecase

import com.opetrola.technicaltestservicebank.application.ports.out.RoleRepositoryPort
import com.opetrola.technicaltestservicebank.domain.model.Role
import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity.RoleEntity

class RoleUseCase(
    private val repository: RoleRepositoryPort
) {

    fun create(role: Role): Role {
        repository.findByName(role.name)
            ?: throw IllegalArgumentException("Role with name ${role.name} not found")

        val newRole = repository.save(
            RoleEntity(
                name = role.name
            )
        )

        return Role(
            id = newRole.id,
            name = newRole.name
        )
    }

    fun findAll(): List<Role> {
        return repository.findAll()
    }
}