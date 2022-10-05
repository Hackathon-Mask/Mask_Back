package meister.hackaton.maskserver.domain.user.exception

import meister.hackaton.maskserver.domain.user.error.UserErrorCode
import meister.hackaton.maskserver.global.error.base.BusinessException

object AlreadyExistsException : BusinessException(UserErrorCode.USER_ALREADY_EXISTS) {

    @JvmField
    val EXCEPTION = AlreadyExistsException

}