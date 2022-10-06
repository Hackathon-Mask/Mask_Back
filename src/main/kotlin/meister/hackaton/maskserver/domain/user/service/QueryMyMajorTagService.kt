package meister.hackaton.maskserver.domain.user.service

import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.util.SecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyMajorTagService(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil
) {

    @Transactional(readOnly = true)
    fun execute(): TagResponse.TagElement {
        val user = userRepository.findUserById(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException.EXCEPTION

        user.let {
            val tag = user.majorTag

            return TagResponse.TagElement(tag.id, tag.name)
        }
    }

}