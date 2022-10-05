package meister.hackaton.maskserver.global.exception

import meister.hackaton.maskserver.global.error.GlobalErrorCode
import meister.hackaton.maskserver.global.error.base.BusinessException

object FileIOInterruptedException : BusinessException(GlobalErrorCode.FILE_IO_INTERRUPTED) {

    @JvmField
    val EXCEPTION = FileIOInterruptedException

}