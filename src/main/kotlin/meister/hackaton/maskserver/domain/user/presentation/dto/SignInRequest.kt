package meister.hackaton.maskserver.domain.user.presentation.dto

import javax.validation.constraints.NotNull

data class SignInRequest(

    @field:NotNull
    val email: String,

    @field:NotNull
    val password: String

)