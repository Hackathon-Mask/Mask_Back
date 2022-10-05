package meister.hackaton.maskserver.global.security.exception

import meister.hackaton.maskserver.global.error.base.BusinessException
import meister.hackaton.maskserver.global.security.error.SecurityErrorCode

object ExpiredTokenException : BusinessException(SecurityErrorCode.EXPIRED_TOKEN) {

    @JvmField
    val EXCEPTION = ExpiredTokenException

}