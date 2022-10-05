package meister.hackaton.maskserver.global.security.error

import meister.hackaton.maskserver.global.error.base.ErrorProperty
import org.springframework.http.HttpStatus

enum class SecurityErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰"),

    UNEXPECTED_TOKEN(HttpStatus.UNAUTHORIZED, "알 수 없는 토큰"),

    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "유형이 맞지 않는 토큰"),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰");

    override fun status(): Int = status.value()
    override fun message(): String = message
}