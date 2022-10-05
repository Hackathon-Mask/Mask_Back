package meister.hackaton.maskserver.global.filter

import meister.hackaton.maskserver.global.security.token.JwtComponent
import meister.hackaton.maskserver.global.security.token.JwtParser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(
    private val jwtParser: JwtParser
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)

        token?.run {
            SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(this)
        }

        filterChain.doFilter(request, response)
    }

    private fun resolvedToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(JwtComponent.HEADER)

        if (bearerToken != null && (bearerToken.startsWith(JwtComponent.PREFIX))) {
            return bearerToken.substring(7)
        }

        return null
    }
}