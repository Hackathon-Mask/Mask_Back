package meister.hackaton.maskserver.domain.tag.exception

import meister.hackaton.maskserver.domain.tag.error.TagErrorCode
import meister.hackaton.maskserver.global.error.base.BusinessException

object TagNotFoundException : BusinessException(TagErrorCode.TAG_NOT_FOUND) {

    @JvmField
    val EXCEPTION = TagNotFoundException

}