package meister.hackaton.maskserver.global.error

import meister.hackaton.maskserver.global.error.dto.ErrorResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalErrorHandler {

    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleDataIntegrityViolationException(
        exception: DataIntegrityViolationException
    ): ErrorResponse? {
        return ErrorResponse.of(exception)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorResponse? {
        return ErrorResponse.of(GlobalErrorCode.BAD_REQUEST)
    }
}