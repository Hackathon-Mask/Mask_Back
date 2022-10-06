package meister.hackaton.maskserver.domain.question.service

import meister.hackaton.maskserver.domain.answer.repository.AnswerRepository
import meister.hackaton.maskserver.domain.question.exception.QuestionNotFoundException
import meister.hackaton.maskserver.domain.question.presentation.dto.QuestionDetailsResponse
import meister.hackaton.maskserver.domain.question.repository.QuestionRepository
import meister.hackaton.maskserver.domain.question.skill.repository.QuestionSkillRepository
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.domain.user.skill.repository.MySkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryQuestionService(
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
    private val mySkillRepository: MySkillRepository,
    private val questionSkillRepository: QuestionSkillRepository
) {

    @Transactional(readOnly = true)
    fun execute(questionId: Long): QuestionDetailsResponse {
        val question = questionRepository.findQuestionById(questionId) ?: throw QuestionNotFoundException.EXCEPTION

        val answerList = answerRepository.findAnswersByQuestionId(questionId)
        val answers = answerList.map {
            val mySkills = mySkillRepository.findMySkillsByIdUserId(it.user.id)

            QuestionDetailsResponse.Answer(
                id = it.id,
                content = it.content,
                createdAt = it.createdAt.toLocalDate(),
                writer = QuestionDetailsResponse.AnswerWriter(
                    id = it.user.id,
                    name = it.user.name,
                    skills = mySkills.map { mySkill ->
                        TagResponse.TagElement(
                            id = mySkill.skillTag.id,
                            name = mySkill.skillTag.name
                        )
                    },
                    majorTag = TagResponse.TagElement(
                        id = it.user.majorTag.id,
                        name = it.user.majorTag.name
                    ),
                )
            )
        }

        val questionSkills = questionSkillRepository.findQuestionSkillsByIdQuestionId(questionId)
        val skills = questionSkills.map {
            TagResponse.TagElement(
                id = it.skillTag.id,
                name = it.skillTag.name
            )
        }

        return QuestionDetailsResponse(
            id = question.id,
            title = question.title,
            content = question.content,
            titleImage = question.titleImage,
            status = question.status,
            majorTag = TagResponse.TagElement(
                id = question.majorTag.id,
                name = question.majorTag.name
            ),
            skills = skills,
            curiousityCount = question.curiousityCount,
            answerCount = question.answerCount,
            createdAt = question.createdAt.toLocalDate(),
            writer = QuestionDetailsResponse.QuestionWriter(
                id = question.user.id,
                name = question.user.name,
            ),
            answers = answers
        )
    }

}