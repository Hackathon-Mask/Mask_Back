package meister.hackaton.maskserver.domain.user.presentation.dto

import meister.hackaton.maskserver.domain.user.model.School
import meister.hackaton.maskserver.domain.user.model.UserType
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class SignUpRequest(
    @field:NotNull
    @field:Length(max = 20)
    val name: String,

    @field:NotNull
    val email: String,

    @field:NotNull
    val password: String,

    @field:NotNull
    val school: School,

    @field:NotNull
    val type: UserType,

    @field:NotNull
    val generation: Int,

    @field:NotNull
    @field:Length(max = 20)
    val company: String,

    @field:NotNull
    @field:Length(max = 20)
    val club: String,

    @field:NotNull
    val majorTag: Long,

    @field:NotNull
    val skills: List<Long>
)
