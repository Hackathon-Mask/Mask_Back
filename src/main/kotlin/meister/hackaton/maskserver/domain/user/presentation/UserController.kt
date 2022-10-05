package meister.hackaton.maskserver.domain.user.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.user.presentation.dto.SignInRequest
import meister.hackaton.maskserver.domain.user.presentation.dto.SignUpRequest
import meister.hackaton.maskserver.domain.user.presentation.dto.TokenResponse
import meister.hackaton.maskserver.domain.user.service.SignInService
import meister.hackaton.maskserver.domain.user.service.SignUpService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "유저", description = "유저 관련 API 입니다.")
@RequestMapping("/users")
@RestController
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService
) {

    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUp(@RequestBody @Valid request: SignUpRequest) {
        signUpService.execute(request)
    }

    @Operation(summary = "로그인")
    @PostMapping("/tokens")
    fun signIn(@RequestBody @Valid request: SignInRequest): TokenResponse {
        return signInService.execute(request)
    }

}