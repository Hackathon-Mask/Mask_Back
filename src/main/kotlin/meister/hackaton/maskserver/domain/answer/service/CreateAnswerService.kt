package meister.hackaton.maskserver.domain.answer.service

import meister.hackaton.maskserver.domain.answer.model.Answer
import meister.hackaton.maskserver.domain.answer.presentation.dto.CreateAnswerRequest
import meister.hackaton.maskserver.domain.answer.repository.AnswerRepository
import meister.hackaton.maskserver.domain.question.exception.QuestionNotFoundException
import meister.hackaton.maskserver.domain.question.presentation.dto.QuestionDetailsResponse
import meister.hackaton.maskserver.domain.question.repository.QuestionRepository
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.domain.user.skill.repository.MySkillRepository
import meister.hackaton.maskserver.global.util.SecurityUtil

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateAnswerService(
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
    private val mySkillRepository: MySkillRepository,
    private val securityUtil: SecurityUtil
) {

    @Transactional
    fun execute(questionId: Long, request: CreateAnswerRequest): QuestionDetailsResponse.Answer {
        val user = userRepository.findUserById(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException.EXCEPTION
        val question = questionRepository.findQuestionById(questionId) ?: throw QuestionNotFoundException.EXCEPTION

        val answer = Answer(
            question = question,
            user = user,
            content = request.content
        )

        answerRepository.save(answer)
        question.addAnswer()

        val mySkills = mySkillRepository.findMySkillsByIdUserId(user.id).map {
            TagResponse.TagElement(
                id = it.skillTag.id,
                name = it.skillTag.name
            )
        }

        return QuestionDetailsResponse.Answer(
            id = answer.id,
            content = answer.content,
            createdAt = answer.createdAt.toLocalDate(),
            writer = QuestionDetailsResponse.AnswerWriter(
                id = user.id,
                name = user.name,
                majorTag = TagResponse.TagElement(
                    id = user.majorTag.id,
                    name = user.majorTag.name
                ),
                skills = mySkills
            )
        )
    }

}