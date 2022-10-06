package meister.hackaton.maskserver.domain.question.error

import meister.hackaton.maskserver.global.error.base.ErrorProperty
import org.springframework.http.HttpStatus

enum class QuestionErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "질문을 찾을 수 없음");

    override fun status(): Int = status.value()
    override fun message(): String = message
}