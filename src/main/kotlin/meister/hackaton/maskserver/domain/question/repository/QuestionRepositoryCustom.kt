package meister.hackaton.maskserver.domain.question.repository

import meister.hackaton.maskserver.domain.question.model.QueryType
import meister.hackaton.maskserver.domain.question.repository.vo.QuestionVO

interface QuestionRepositoryCustom {

    fun findByQueryType(keyword: String?, majorTag: Long, type: QueryType): List<QuestionVO>

}