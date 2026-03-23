package com.opetrola.technicaltestservicebank.infrastructure.adapters.`in`.rest

import com.opetrola.technicaltestservicebank.application.usecase.AuthenticationUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationUseCase: AuthenticationUseCase
) {

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest):  LoginResponse{
        val token = authenticationUseCase.execute(req.cpf, req.password)
        return LoginResponse(token)
    }
}

data class LoginRequest(val cpf: String, val password: String)

data class LoginResponse(val token: String)