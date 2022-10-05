package meister.hackaton.maskserver.global.security.principle

import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.security.exception.InvalidTokenException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(userId: String): UserDetails {
        val id = userId.toLong()
        val user = userRepository.findUserById(id) ?: throw InvalidTokenException.EXCEPTION

        return AuthDetails(id, user.type)
    }

}