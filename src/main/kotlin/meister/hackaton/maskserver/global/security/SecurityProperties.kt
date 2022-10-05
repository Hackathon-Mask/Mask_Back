package meister.hackaton.maskserver.global.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

@ConfigurationProperties("security")
@ConstructorBinding
class SecurityProperties(
    secretKey: String,
    accessExp: Int
) {

    val accessExpiredTime = accessExp * 1000
    val encodingSecretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())!!

}