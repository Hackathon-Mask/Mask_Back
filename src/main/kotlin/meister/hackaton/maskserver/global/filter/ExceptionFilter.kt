package meister.hackaton.maskserver.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import meister.hackaton.maskserver.global.error.base.BusinessException
import meister.hackaton.maskserver.global.error.base.ErrorProperty
import meister.hackaton.maskserver.global.error.dto.ErrorResponse
import meister.hackaton.maskserver.global.exception.InternalServerErrorException
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BusinessException) {
            writeErrorCode(e.exceptionProperty, response)
        } catch (e: Exception) {
            when (e.cause) {
                is BusinessException -> writeErrorCode((e.cause as BusinessException).exceptionProperty, response)
                else -> {
                    e.printStackTrace()
                    writeErrorCode(InternalServerErrorException.EXCEPTION.exceptionProperty, response)
                }
            }
        }
    }

    private fun writeErrorCode(exception: ErrorProperty, response: HttpServletResponse) {
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.status = exception.status()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(
            objectMapper.writeValueAsString(
                ErrorResponse.of(exception)
            )
        )
    }
}