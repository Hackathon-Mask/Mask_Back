package meister.hackaton.maskserver.domain.user.model

import meister.hackaton.maskserver.domain.tag.model.Tag
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
@Table(name = "tbl_user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_tag_id", nullable = false)
    val majorTag: Tag,

    @field:NotNull
    @field:Length(max = 20)
    @Column(name = "name")
    val name: String,

    @field:NotNull
    @Column(name = "email", unique = true)
    val email: String,

    @field:NotNull
    @Column(name = "password", columnDefinition = "CHAR(60)")
    val password: String,

    @field:NotNull
    @Column(name = "phone_number", unique = true)
    val phoneNumber: String,

    @field:NotNull
    @Column(name = "school", length = 20)
    @Enumerated(EnumType.STRING)
    val school: School,

    @field:NotNull
    @Column(name = "type", length = 8)
    @Enumerated(EnumType.STRING)
    val type: UserType,

    @field:NotNull
    @Column(name = "generation")
    val generation: Int,

    @field:NotNull
    @field:Length(max = 20)
    @Column(name = "belong")
    val belong: String,
)