package meister.hackaton.maskserver.domain.user.service

import meister.hackaton.maskserver.domain.tag.exception.TagNotFoundException
import meister.hackaton.maskserver.domain.tag.model.TagType
import meister.hackaton.maskserver.domain.tag.repository.TagRepository
import meister.hackaton.maskserver.domain.user.exception.AlreadyExistsException
import meister.hackaton.maskserver.domain.user.model.User
import meister.hackaton.maskserver.domain.user.presentation.dto.SignUpRequest
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.domain.user.skill.model.MySkill
import meister.hackaton.maskserver.domain.user.skill.model.MySkillId
import meister.hackaton.maskserver.domain.user.skill.repository.MySkillRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val tagRepository: TagRepository,
    private val mySkillRepository: MySkillRepository
) {

    @Transactional
    fun execute(request: SignUpRequest) {
        if (userRepository.existsByEmail(request.email)) {
            throw AlreadyExistsException.EXCEPTION
        }

        val tag = tagRepository.findTagByIdAndType(request.majorTag, TagType.MAJOR)
            ?: throw TagNotFoundException.EXCEPTION

        val user = User(
            majorTag = tag,
            name = request.name,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            school = request.school,
            type = request.type,
            generation = request.generation,
            belong = request.belong
        )

        userRepository.save(user)

        val mySkills: MutableList<MySkill> = mutableListOf()
        for (skillId in request.skills) {
            val skill = tagRepository.findTagByIdAndType(skillId, TagType.SKILL) ?: throw TagNotFoundException.EXCEPTION

            val mySkill = MySkill(
                MySkillId(
                    userId = user.id,
                    skillTagId = skillId
                ),
                user,
                skill
            )

            mySkills.add(mySkill)
        }

        mySkillRepository.saveAll(mySkills)
    }
}