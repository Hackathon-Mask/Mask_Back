package meister.hackaton.maskserver.domain.question.service

import meister.hackaton.maskserver.domain.question.event.QuestionCreationEvent
import meister.hackaton.maskserver.domain.question.model.Question
import meister.hackaton.maskserver.domain.question.model.QuestionStatus
import meister.hackaton.maskserver.domain.question.presentation.dto.CreateQuestionRequest
import meister.hackaton.maskserver.domain.question.repository.QuestionRepository
import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkill
import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkillId
import meister.hackaton.maskserver.domain.question.skill.repository.QuestionSkillRepository
import meister.hackaton.maskserver.domain.tag.exception.TagNotFoundException
import meister.hackaton.maskserver.domain.tag.model.TagType
import meister.hackaton.maskserver.domain.tag.repository.TagRepository
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.util.SecurityUtil
import meister.hackaton.maskserver.thirdparty.message.MessageSender
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateQuestionService(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
    private val tagRepository: TagRepository,
    private val questionSkillRepository: QuestionSkillRepository,
    private val securityUtil: SecurityUtil,
    private val eventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun execute(request: CreateQuestionRequest) {
        val user = userRepository.findUserById(securityUtil.getCurrentUserId())
            ?: throw UserNotFoundException.EXCEPTION

        val tag = tagRepository.findTagByIdAndType(request.majorTag, TagType.MAJOR)
            ?: throw TagNotFoundException.EXCEPTION

        val question = Question(
            user = user,
            majorTag = tag,
            title = request.title,
            content = request.content,
            summary = request.summary,
            titleImage = request.titleImage,
            status = QuestionStatus.CREATED
        )

        questionRepository.save(question)

        val questionSkills: MutableList<QuestionSkill> = mutableListOf()
        for (skillId in request.skills) {
            val skill = tagRepository.findTagByIdAndType(skillId, TagType.SKILL) ?: throw TagNotFoundException.EXCEPTION

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

        val eventUsers = userRepository.findUsersByMajorTag(tag).map { it.phoneNumber }
        eventPublisher.publishEvent(QuestionCreationEvent(tag.name, user.phoneNumber, eventUsers))
    }

}