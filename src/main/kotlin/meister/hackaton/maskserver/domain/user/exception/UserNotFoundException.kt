package meister.hackaton.maskserver.domain.user.exception

import meister.hackaton.maskserver.domain.user.error.UserErrorCode
import meister.hackaton.maskserver.global.error.base.BusinessException

object UserNotFoundException : BusinessException(UserErrorCode.USER_NOT_FOUND) {

    @JvmField
    val EXCEPTION = UserNotFoundException

}