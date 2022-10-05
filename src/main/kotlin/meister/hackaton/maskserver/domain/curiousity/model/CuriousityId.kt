package meister.hackaton.maskserver.domain.curiousity.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
data class CuriousityId(

    @field:NotNull
    @Column(name = "user_id")
    val userId: Long,

    @field:NotNull
    @Column(name = "question_id")
    val questionId: Long

) : Serializable