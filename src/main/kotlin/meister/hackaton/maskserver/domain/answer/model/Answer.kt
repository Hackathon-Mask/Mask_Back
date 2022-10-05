package meister.hackaton.maskserver.domain.answer.model

import meister.hackaton.maskserver.domain.question.model.Question
import meister.hackaton.maskserver.global.entity.BaseTimeEntity
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_answer")
class Answer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    val question: Question,

    @field:NotNull
    @field:Length(max = 10000)
    @Column(name = "content")
    val content: String

) : BaseTimeEntity()