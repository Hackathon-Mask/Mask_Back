package meister.hackaton.maskserver.global.exception

import meister.hackaton.maskserver.global.error.GlobalErrorCode
import meister.hackaton.maskserver.global.error.base.BusinessException

object InternalServerErrorException : BusinessException(GlobalErrorCode.INTERNAL_SERVER_ERROR) {

    @JvmField
    val EXCEPTION = InternalServerErrorException

}