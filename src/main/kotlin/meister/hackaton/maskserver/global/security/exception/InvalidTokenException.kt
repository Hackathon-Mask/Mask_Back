package meister.hackaton.maskserver.global.security.exception

import meister.hackaton.maskserver.global.error.base.BusinessException
import meister.hackaton.maskserver.global.security.error.SecurityErrorCode

object InvalidTokenException : BusinessException(SecurityErrorCode.INVALID_TOKEN) {

    @JvmField
    val EXCEPTION = InvalidTokenException

}