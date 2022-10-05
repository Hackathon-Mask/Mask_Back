package meister.hackaton.maskserver.domain.tag.model

import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_tag")
class Tag(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotNull
    @field:Length(max = 20)
    @Column(name = "name")
    val name: String,

    @field:NotNull
    @Column(name = "type", columnDefinition = "CHAR(5)")
    @Enumerated(EnumType.STRING)
    val type: TagType

)