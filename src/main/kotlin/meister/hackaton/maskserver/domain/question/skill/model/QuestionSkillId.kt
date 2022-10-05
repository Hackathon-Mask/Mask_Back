package meister.hackaton.maskserver.domain.question.skill.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
data class QuestionSkillId(

    @field:NotNull
    @Column(name = "question_id")
    val questionId: Long,

    @field:NotNull
    @Column(name = "skill_tag_id")
    val skillTagId: Long

) : Serializable