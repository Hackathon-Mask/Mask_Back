package meister.hackaton.maskserver.domain.user.error

import meister.hackaton.maskserver.global.error.base.ErrorProperty
import org.springframework.http.HttpStatus

enum class UserErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "유저가 이미 존재함"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음");

    override fun status(): Int = status.value()
    override fun message(): String = message
}