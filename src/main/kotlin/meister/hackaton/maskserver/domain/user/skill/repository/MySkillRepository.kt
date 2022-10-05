package meister.hackaton.maskserver.domain.user.skill.repository

import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkill
import meister.hackaton.maskserver.domain.question.skill.model.QuestionSkillId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MySkillRepository : CrudRepository<QuestionSkill, QuestionSkillId> {
}