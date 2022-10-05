package meister.hackaton.maskserver.global.security.principle

import meister.hackaton.maskserver.domain.user.model.UserType
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val userId: Long,
    private val authority: UserType
) : UserDetails {
    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority(authority.name))

    override fun getPassword() = null

    override fun getUsername() = userId.toString()

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}