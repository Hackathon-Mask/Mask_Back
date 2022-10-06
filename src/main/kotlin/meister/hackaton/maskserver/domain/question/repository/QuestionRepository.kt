package meister.hackaton.maskserver.domain.question.repository

import meister.hackaton.maskserver.domain.question.model.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : CrudRepository<Question, Long>, QuestionRepositoryCustom {

    fun findQuestionById(id: Long): Question?

}