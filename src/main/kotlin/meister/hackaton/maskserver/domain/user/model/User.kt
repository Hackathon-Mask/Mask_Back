package meister.hackaton.maskserver.domain.user.model

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
@Table(name = "tbl_user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotNull
    @Length(max = 20)
    @Column(name = "name")
    val name: String,

    @NotNull
    @Column(name = "email", unique = true)
    val email: String,

    @NotNull
    @Column(name = "password", columnDefinition = "CHAR(60)")
    val password: String,

    @NotNull
    @Length(max = 20)
    @Column(name = "school")
    @Enumerated(EnumType.STRING)
    val school: School,

    @NotNull
    @Length(max = 8)
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: UserType,

    @NotNull
    @Column(name = "generation")
    val generation: Int,

    @NotNull
    @Column(name = "company")
    val company: String,

    @NotNull
    @Column(name = "club")
    val club: String
)