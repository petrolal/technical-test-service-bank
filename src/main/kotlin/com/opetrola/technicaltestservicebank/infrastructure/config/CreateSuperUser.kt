package com.opetrola.technicaltestservicebank.infrastructure.config

import com.opetrola.technicaltestservicebank.application.ports.out.UserRepositoryPort
import com.opetrola.technicaltestservicebank.domain.model.User
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class CreateSuperUser(
    private val userRepositoryPort: UserRepositoryPort,
    private val passwordEncoder: PasswordEncoder
): ApplicationRunner {

    private val logger: Logger = Logger.getLogger(CreateSuperUser::class.java.name)

    fun createSuperUser() {
        val user = userRepositoryPort.findByCpf("05707824146")

        if (user == null) {
            logger.info("Creating a new User")

            val newUser = User(
                name = "Admin",
                cpf = "05707824146",
                password = passwordEncoder.encode("admin"),
                active = true,
                roles = listOf()
            )

            userRepositoryPort.save(newUser)
        } else {
            logger.info("User Already created")
        }
    }

    override fun run(args: ApplicationArguments) {
        createSuperUser()
    }
}