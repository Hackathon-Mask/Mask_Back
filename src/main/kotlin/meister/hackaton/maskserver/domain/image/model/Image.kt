package meister.hackaton.maskserver.domain.image.model

import meister.hackaton.maskserver.domain.question.model.Question
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_image")
class Image(

    @Id
    val questionId: Long,

    @MapsId
    @field:NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    val question: Question

)