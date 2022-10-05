package meister.hackaton.maskserver.domain.curiousity.model

import meister.hackaton.maskserver.domain.question.model.Question
import meister.hackaton.maskserver.domain.user.model.User
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(name = "tbl_curiousity")
class Curiousity(

    @EmbeddedId
    val id: CuriousityId,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    val question: Question

)