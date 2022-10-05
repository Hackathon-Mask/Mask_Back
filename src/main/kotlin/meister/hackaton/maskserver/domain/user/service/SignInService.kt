package meister.hackaton.maskserver.domain.user.service

import meister.hackaton.maskserver.domain.user.exception.InvalidPasswordException
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.presentation.dto.SignInRequest
import meister.hackaton.maskserver.domain.user.presentation.dto.TokenResponse
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.security.token.JwtProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignInService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: JwtProvider
) {

    @Transactional
    fun execute(request: SignInRequest): TokenResponse {
        val user = userRepository.findUserByEmail(request.email) ?: throw UserNotFoundException.EXCEPTION

        if (!passwordEncoder.matches(user.password, request.password)) {
            throw InvalidPasswordException.EXCEPTION
        }

        return TokenResponse(
            accessToken = tokenProvider.generateAccessToken(user.id!!, user.type)
        )
    }

}