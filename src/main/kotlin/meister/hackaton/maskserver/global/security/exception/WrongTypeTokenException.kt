package meister.hackaton.maskserver.global.security.exception

import meister.hackaton.maskserver.global.error.base.BusinessException
import meister.hackaton.maskserver.global.security.error.SecurityErrorCode

object WrongTypeTokenException : BusinessException(SecurityErrorCode.WRONG_TYPE_TOKEN) {

    @JvmField
    val EXCEPTION = WrongTypeTokenException

}