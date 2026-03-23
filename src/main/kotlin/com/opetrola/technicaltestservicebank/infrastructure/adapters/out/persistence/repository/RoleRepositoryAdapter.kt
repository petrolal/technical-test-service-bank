package com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.repository

import com.opetrola.technicaltestservicebank.application.ports.out.RoleRepositoryPort
import com.opetrola.technicaltestservicebank.domain.model.Role
import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity.RoleEntity
import org.springframework.stereotype.Component

@Component
class RoleRepositoryAdapter (
    private val repository: RoleJpaRepository
) : RoleRepositoryPort {
    override fun findByName(name: String): RoleEntity {
        val entity = repository.findByName(name)
            ?: throw RuntimeException("Role with name '$name' does not exist.")

        return entity
    }

    override fun save(role: RoleEntity): RoleEntity {
         return repository.save(role)
    }

    override fun findAll(): List<Role> {
        return repository.findAll().map {
            Role(
                name = it.name,
                id = it.id
            )
        }
    }
}