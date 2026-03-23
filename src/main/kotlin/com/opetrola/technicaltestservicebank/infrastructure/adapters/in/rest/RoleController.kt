package com.opetrola.technicaltestservicebank.infrastructure.adapters.`in`.rest

import com.opetrola.technicaltestservicebank.application.usecase.RoleUseCase
import com.opetrola.technicaltestservicebank.domain.model.Role
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/role")
class RoleController(
    private val useCase: RoleUseCase
) {

    @PostMapping
    fun create(@RequestBody role: Role): Role {
        return useCase.create(role)
    }

    @GetMapping
    fun findAll(): List<Role> {
        return useCase.findAll()
    }
}