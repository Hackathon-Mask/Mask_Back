package meister.hackaton.maskserver.domain.user.skill.model

import meister.hackaton.maskserver.domain.tag.model.Tag
import meister.hackaton.maskserver.domain.user.model.User
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(name = "tbl_my_skill")
class MySkill(

    @EmbeddedId
    val id: MySkillId,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @MapsId("skillTagId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_tag_id", nullable = false)
    val skillTag: Tag

)