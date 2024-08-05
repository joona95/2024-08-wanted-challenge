package org.project.portfolio.common

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {

    @Value("\${jwt.secret-key}")
    lateinit var secretKey: String

    @Value("\${jwt.token-validity-in-ms}")
    lateinit var tokenValidity: Integer

    fun createToken(id: Long): String {

        val now = Date()
        val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))

        return Jwts.builder()
            .setSubject(id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenValidity.toLong()))
            .signWith(key)
            .compact()
    }

    fun resolveToken(request: HttpServletRequest?): String? {
        return request?.getHeader("Authorization")?.substring(7)
    }

    fun isValidToken(token: String): Boolean {

        return try {
            return !getClaims(token).body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getId(token: String): Long {

       return getClaims(token).body.subject.toLong();
    }

    private fun getClaims(token: String): Jws<Claims> {

        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
    }
}