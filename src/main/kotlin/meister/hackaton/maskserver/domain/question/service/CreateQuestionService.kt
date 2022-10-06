package meister.hackaton.maskserver.domain.question.service

import meister.hackaton.maskserver.domain.question.model.Question
import meister.hackaton.maskserver.domain.question.presentation.dto.CreateQuestionRequest
import meister.hackaton.maskserver.domain.question.repository.QuestionRepository
import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkill
import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkillId
import meister.hackaton.maskserver.domain.question.skill.repository.QuestionSkillRepository
import meister.hackaton.maskserver.domain.tag.exception.TagNotFoundException
import meister.hackaton.maskserver.domain.tag.repository.TagRepository
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.util.SecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateQuestionService(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
    private val tagRepository: TagRepository,
    private val questionSkillRepository: QuestionSkillRepository,
    private val securityUtil: SecurityUtil
) {

    @Transactional
    fun execute(request: CreateQuestionRequest) {
        val user = userRepository.findUserById(securityUtil.getCurrentUserId())
            ?: throw UserNotFoundException.EXCEPTION

        val tag = tagRepository.findTagById(request.majorTag)
            ?: throw TagNotFoundException.EXCEPTION

        val question = Question(
            user = user,
            majorTag = tag,
            title = request.title,
            content = request.content,
            summary = request.summary,
            titleImage = request.titleImage,
        )

        questionRepository.save(question)

        val questionSkills: MutableList<QuestionSkill> = mutableListOf()
        for (skillId in request.skills) {
            val skill = tagRepository.findTagById(skillId) ?: throw TagNotFoundException.EXCEPTION

            val questionSkill = QuestionSkill(
                QuestionSkillId(
                    questionId = question.id,
                    skillTagId = skillId
                ),
                question,
                skill
            )

            questionSkills.add(questionSkill)
        }

        questionSkillRepository.saveAll(questionSkills)
    }

}