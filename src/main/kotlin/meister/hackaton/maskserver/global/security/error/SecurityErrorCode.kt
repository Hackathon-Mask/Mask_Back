package meister.hackaton.maskserver.global.security.error

import meister.hackaton.maskserver.global.error.base.ErrorProperty
import org.springframework.http.HttpStatus

enum class SecurityErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰");

    override fun status(): Int = status.value()
    override fun message(): String = message
}