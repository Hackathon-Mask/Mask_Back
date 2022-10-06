package meister.hackaton.maskserver.domain.question.service

import meister.hackaton.maskserver.domain.question.model.QueryType
import meister.hackaton.maskserver.domain.question.presentation.dto.QuestionResponse
import meister.hackaton.maskserver.domain.question.repository.QuestionRepository
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryQuestionsService(
    private val questionRepository: QuestionRepository
) {

    @Transactional(readOnly = true)
    fun execute(keyword: String?, majorTag: Long, queryType: QueryType): QuestionResponse {
        val questions = questionRepository.findByQueryType(
            keyword, majorTag, queryType
        )

        val result = questions.map {
            QuestionResponse.QuestionElement(
                id = it.id,
                title = it.title,
                summary = it.summary,
                titleImage = it.titleImage,
                status = it.status,
                majorTag = TagResponse.TagElement(
                    id = it.majorTagId,
                    name = it.majorTagName
                ),
                curiousityCount = it.curiousityCount
            )
        }

        return QuestionResponse(result)
    }

}