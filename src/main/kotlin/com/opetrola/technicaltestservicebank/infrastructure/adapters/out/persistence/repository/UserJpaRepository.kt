package com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.repository

import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserJpaRepository : JpaRepository<UserEntity, UUID> {
    fun findByCpf(cpf: String): UserEntity?
}