package meister.hackaton.maskserver.global.security.token

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import meister.hackaton.maskserver.domain.user.model.UserType
import meister.hackaton.maskserver.global.security.SecurityProperties
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider(
    private val securityProperties: SecurityProperties,
) {

    fun generateAccessToken(userId: Long, authority: UserType): String =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.encodingSecretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtComponent.ACCESS)
            .setId(userId.toString())
            .claim(JwtComponent.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExpiredTime))
            .compact()

}