package meister.hackaton.maskserver.domain.user.skill.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
data class MySkillId(

    @field:NotNull
    @Column(name = "user_id")
    val userId: Long,

    @field:NotNull
    @Column(name = "skill_tag_id")
    val skillTagId: Long

) : Serializable