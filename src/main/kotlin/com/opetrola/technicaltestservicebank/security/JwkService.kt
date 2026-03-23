package com.opetrola.technicaltestservicebank.security

import com.opetrola.technicaltestservicebank.domain.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class JwkService {

    private val secret = "minha-chave-super-secreta-com-mais-de-32-bytes-123456"
    val key = SecretKeySpec(secret.toByteArray(), SignatureAlgorithm.HS256.jcaName)

    fun generateToken(user: User): String {
        val now = Date()
        val expiry = Date(now.time + 1000 * 60 * 60) // 1h

        val roles = user.roles.map { it.name }

        return Jwts.builder()
            .setSubject(user.cpf)
            .claim("roles", roles)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(key)
            .compact()
    }
}