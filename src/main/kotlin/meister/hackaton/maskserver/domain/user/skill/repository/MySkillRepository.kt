package meister.hackaton.maskserver.domain.user.skill.repository

import meister.hackaton.maskserver.domain.user.skill.model.MySkill
import meister.hackaton.maskserver.domain.user.skill.model.MySkillId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MySkillRepository : CrudRepository<MySkill, MySkillId> {

    fun findMySkillsByIdUserId(userId: Long): List<MySkill>

}