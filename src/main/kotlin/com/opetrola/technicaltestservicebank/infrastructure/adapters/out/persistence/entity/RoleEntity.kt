package com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
@Table(name = "roles", schema = "public")
data class RoleEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @NotBlank(message = "Name cannot be empty")
    var name: String,
)