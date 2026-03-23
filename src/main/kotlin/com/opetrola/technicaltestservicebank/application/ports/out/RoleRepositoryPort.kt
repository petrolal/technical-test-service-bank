package com.opetrola.technicaltestservicebank.application.ports.out

import com.opetrola.technicaltestservicebank.domain.model.Role
import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity.RoleEntity

interface RoleRepositoryPort {
    fun findByName(name: String): RoleEntity?
    fun save(role: RoleEntity): RoleEntity
    fun findAll(): List<Role>
}