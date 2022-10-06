package meister.hackaton.maskserver.domain.question.service

import meister.hackaton.maskserver.domain.question.presentation.dto.QuestionResponse
import meister.hackaton.maskserver.domain.question.repository.QuestionRepository
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.global.util.SecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyQuestionsService(
    private val questionRepository: QuestionRepository,
    private val securityUtil: SecurityUtil
) {

    @Transactional(readOnly = true)
    fun execute(): QuestionResponse {
        val questions = questionRepository.findQuestionsByUserIdOrderByCreatedAtDesc(securityUtil.getCurrentUserId())

        val result = questions.map {
            QuestionResponse.QuestionElement(
                id = it.id,
                title = it.title,
                summary = it.summary,
                titleImage = it.titleImage,
                status = it.status,
                majorTag = TagResponse.TagElement(
                    id = it.majorTag.id,
                    name = it.majorTag.name
                ),
                curiousityCount = it.curiousityCount
            )
        }

        return QuestionResponse(result)
    }

}