package com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.repository

import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleJpaRepository : JpaRepository<RoleEntity, UUID> {
    fun findByName(name: String): RoleEntity?
    fun save(role: RoleEntity): RoleEntity
}