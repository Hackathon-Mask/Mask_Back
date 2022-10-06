package meister.hackaton.maskserver.domain.curiousity.service

import meister.hackaton.maskserver.domain.curiousity.model.Curiousity
import meister.hackaton.maskserver.domain.curiousity.model.CuriousityId
import meister.hackaton.maskserver.domain.curiousity.repository.CuriousityRepository
import meister.hackaton.maskserver.domain.question.exception.QuestionNotFoundException
import meister.hackaton.maskserver.domain.question.repository.QuestionRepository
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.util.SecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddCuriousityService(
    private val curiousityRepository: CuriousityRepository,
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil
) {

    @Transactional
    fun execute(questionId: Long) {
        val user = userRepository.findUserById(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException.EXCEPTION
        val question = questionRepository.findQuestionById(questionId) ?: throw QuestionNotFoundException.EXCEPTION

        val curiousity = Curiousity(
            CuriousityId(
                userId = user.id,
                questionId = question.id
            ),
            user = user,
            question = question
        )

        curiousityRepository.save(curiousity)
        question.addCuriousityCount()
    }

}