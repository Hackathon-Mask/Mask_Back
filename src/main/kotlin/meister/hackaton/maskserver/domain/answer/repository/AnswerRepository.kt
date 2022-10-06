package meister.hackaton.maskserver.domain.answer.repository

import meister.hackaton.maskserver.domain.answer.model.Answer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : CrudRepository<Answer, Long> {

    fun findAnswersByQuestionId(questionId: Long): List<Answer>

}