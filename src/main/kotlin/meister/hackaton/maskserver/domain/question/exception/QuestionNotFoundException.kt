package meister.hackaton.maskserver.domain.question.exception

import meister.hackaton.maskserver.domain.question.error.QuestionErrorCode
import meister.hackaton.maskserver.global.error.base.BusinessException

object QuestionNotFoundException : BusinessException(QuestionErrorCode.QUESTION_NOT_FOUND) {

    @JvmField
    val EXCEPTION = QuestionNotFoundException

}