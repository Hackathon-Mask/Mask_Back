package meister.hackaton.maskserver.domain.question.skill.repository

import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkill
import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkillId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionSkillRepository : CrudRepository<QuestionSkill, QuestionSkillId> {
}