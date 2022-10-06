package meister.hackaton.maskserver.domain.question.model

import meister.hackaton.maskserver.domain.tag.model.Tag
import meister.hackaton.maskserver.domain.user.model.User
import meister.hackaton.maskserver.global.entity.BaseTimeEntity
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_question")
class Question(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_tag_id", nullable = false)
    val majorTag: Tag,

    @field:NotNull
    @field:Length(max = 30)
    @Column(name = "title")
    val title: String,

    @field:NotNull
    @field:Length(max = 10000)
    @Column(name = "content")
    val content: String,

    @field:NotNull
    @Column(name = "summary")
    val summary: String,

    @field:NotNull
    @Column(name = "title_image")
    val titleImage: String,

    @field:NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: QuestionStatus

) : BaseTimeEntity() {

    @field:NotNull
    @Column(name = "curiousity_count")
    var curiousityCount: Int = 0

    @field:NotNull
    @Column(name = "answer_count")
    var answerCount: Int = 0

    fun addCuriousityCount() {
        this.curiousityCount++
    }

    fun minusCuriousityCount() {
        this.curiousityCount--
    }

    fun addAnswerCount() {
        this.answerCount++
    }

}