package meister.hackaton.maskserver.domain.question.skill.model

import meister.hackaton.maskserver.domain.question.model.Question
import meister.hackaton.maskserver.domain.tag.model.Tag
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(name = "tbl_question_skill")
class QuestionSkill(

    @EmbeddedId
    val id: QuestionSkillId,

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    val question: Question,

    @MapsId("skillTagId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_tag_id", nullable = false)
    val skillTag: Tag

)