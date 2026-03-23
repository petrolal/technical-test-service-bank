package com.opetrola.technicaltestservicebank.infrastructure.adapters.`in`.rest

import com.opetrola.technicaltestservicebank.application.usecase.UserUseCase
import com.opetrola.technicaltestservicebank.domain.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/user")
class UserController(
    private val useCase: UserUseCase
) {

    @GetMapping
    fun getUsers(): List<User> {
        return useCase.findAll()
    }

}