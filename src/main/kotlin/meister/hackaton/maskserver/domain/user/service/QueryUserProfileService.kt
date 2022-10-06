package meister.hackaton.maskserver.domain.user.service

import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.presentation.dto.UserProfileResponse
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.domain.user.skill.repository.MySkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryUserProfileService(
    private val userRepository: UserRepository,
    private val mySkillRepository: MySkillRepository
) {

    @Transactional(readOnly = true)
    fun execute(userId: Long): UserProfileResponse {
        val user = userRepository.findUserById(userId) ?: throw UserNotFoundException.EXCEPTION

        return UserProfileResponse(
            userId = user.id,
            name = user.name,
            school = user.school.name,
            generation = user.generation,
            belong = user.belong,
            email = user.email,
            majorTag = TagResponse.TagElement(
                id = user.majorTag.id,
                name = user.majorTag.name
            ),
            skills = mySkillRepository.findMySkillsByIdUserId(userId).map { mySkill ->
                TagResponse.TagElement(
                    id = mySkill.skillTag.id,
                    name = mySkill.skillTag.name
                )
            }
        )
    }

}