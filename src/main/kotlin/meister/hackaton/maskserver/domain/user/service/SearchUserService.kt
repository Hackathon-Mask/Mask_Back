package meister.hackaton.maskserver.domain.user.service

import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.domain.user.model.School
import meister.hackaton.maskserver.domain.user.presentation.dto.SearchUserResponse
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.domain.user.skill.repository.MySkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchUserService(
    private val userRepository: UserRepository,
    private val mySkillRepository: MySkillRepository
) {

    @Transactional(readOnly = true)
    fun execute(keyword: String?, school: School): SearchUserResponse {
        val users = userRepository.findUsersByNameContainingAndSchoolOrderByName(keyword, school)

        return SearchUserResponse(
            users.map {
                SearchUserResponse.UserElement(
                    id = it.id,
                    name = it.name,
                    school = it.school.name,
                    generation = it.generation,
                    belong = it.belong,
                    majorTag = TagResponse.TagElement(
                        id = it.majorTag.id,
                        name = it.majorTag.name
                    ),
                    skills = mySkillRepository.findMySkillsByIdUserId(it.id).map { mySkill ->
                        TagResponse.TagElement(
                            id = mySkill.skillTag.id,
                            name = mySkill.skillTag.name
                        )
                    }
                )
            }
        )
    }

}