package meister.hackaton.maskserver.domain.user.exception

import meister.hackaton.maskserver.domain.user.error.UserErrorCode
import meister.hackaton.maskserver.global.error.base.BusinessException

object InvalidPasswordException : BusinessException(UserErrorCode.INVALID_PASSWORD) {

    @JvmField
    val EXCEPTION = InvalidPasswordException

}