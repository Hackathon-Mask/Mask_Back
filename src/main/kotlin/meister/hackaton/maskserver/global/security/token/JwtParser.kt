package meister.hackaton.maskserver.global.security.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import meister.hackaton.maskserver.global.exception.InternalServerErrorException
import meister.hackaton.maskserver.global.security.SecurityProperties
import meister.hackaton.maskserver.global.security.exception.ExpiredTokenException
import meister.hackaton.maskserver.global.security.exception.InvalidTokenException
import meister.hackaton.maskserver.global.security.exception.UnexpectedTokenException
import meister.hackaton.maskserver.global.security.exception.WrongTypeTokenException
import meister.hackaton.maskserver.global.security.principle.AuthDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtParser(
    private val securityProperties: SecurityProperties,
    private val authDetailsService: AuthDetailsService
) {

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parser()
                .setSigningKey(securityProperties.encodingSecretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw InvalidTokenException.EXCEPTION
                is ExpiredJwtException -> throw ExpiredTokenException.EXCEPTION
                is JwtException -> throw UnexpectedTokenException.EXCEPTION
                else -> throw InternalServerErrorException.EXCEPTION
            }
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtComponent.ACCESS) {
            throw WrongTypeTokenException.EXCEPTION
        }

        val details = authDetailsService.loadUserByUsername(claims.body.id)

        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }

}