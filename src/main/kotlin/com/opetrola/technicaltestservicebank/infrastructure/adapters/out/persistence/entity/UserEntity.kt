package com.opetrola.technicaltestservicebank.infrastructure.adapters.out.persistence.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
@Table(name = "users", schema = "public")
data class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @NotBlank(message = "Name cannot be empty")
    val name: String,

    @NotBlank(message = "Email cannot be empty")
    val cpf: String,

    @NotBlank(message = "Password cannot be empty")
    val password: String?,

    val active: Boolean = true,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [(JoinColumn(name = "user_id"))],
        inverseJoinColumns = [(JoinColumn(name = "role_id"))]
    )
    val roles: List<RoleEntity>,
)