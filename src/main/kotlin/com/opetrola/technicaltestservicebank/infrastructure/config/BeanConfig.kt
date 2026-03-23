package com.opetrola.technicaltestservicebank.infrastructure.config

import com.opetrola.technicaltestservicebank.application.ports.out.RoleRepositoryPort
import com.opetrola.technicaltestservicebank.application.ports.out.UserRepositoryPort
import com.opetrola.technicaltestservicebank.application.usecase.AuthenticationUseCase
import com.opetrola.technicaltestservicebank.application.usecase.RoleUseCase
import com.opetrola.technicaltestservicebank.application.usecase.UserUseCase
import com.opetrola.technicaltestservicebank.security.JwkService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BeanConfig {

    @Bean
    fun authenticateUserUseCase (
        repo: UserRepositoryPort,
        encoder: PasswordEncoder
    ) : AuthenticationUseCase {
        return AuthenticationUseCase(repo, encoder, JwkService())
    }

    @Bean
    fun roleUseCase (
        repo: RoleRepositoryPort
    ) : RoleUseCase {
        return RoleUseCase(repo)
    }

    @Bean
    fun userUseCase (
        repo: UserRepositoryPort
    ) : UserUseCase {
        return UserUseCase(repo)
    }
}