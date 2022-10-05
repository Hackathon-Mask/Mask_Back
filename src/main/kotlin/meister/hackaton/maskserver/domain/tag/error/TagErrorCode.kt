package meister.hackaton.maskserver.domain.tag.error

import meister.hackaton.maskserver.global.error.base.ErrorProperty
import org.springframework.http.HttpStatus

enum class TagErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "태그를 찾을 수 없음");

    override fun status(): Int = status.value()
    override fun message(): String = message
}