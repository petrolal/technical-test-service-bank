package com.opetrola.technicaltestservicebank.domain.model

import com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity.RoleEntity
import java.util.*

data class User(
    var id: UUID? = null,
    var name: String,
    var password: String?,
    var cpf: String,
    var active: Boolean,
    var roles: List<RoleEntity>,
)