package meister.hackaton.maskserver.global.error.dto

import meister.hackaton.maskserver.global.error.base.ErrorProperty
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import meister.hackaton.maskserver.global.error.GlobalErrorCode
import javax.validation.ConstraintViolationException

class ErrorResponse(
    val status: Int,
    val message: String,
    val fieldErrors: List<CustomFieldError>
) {

    companion object {
        fun of(exception: ErrorProperty) = ErrorResponse(
            status = exception.status(),
            message = exception.message(),
            fieldErrors = emptyList()
        )

        fun of(bindingResult: BindingResult): ErrorResponse = of(
            exception = GlobalErrorCode.BAD_REQUEST,
            fieldErrors = CustomFieldError.of(bindingResult)
        )

        fun of(exception: ConstraintViolationException): ErrorResponse {
            val fieldErrors = exception.constraintViolations.flatMap { violation ->
                val path = violation.propertyPath
                println(path)
                val field = path.last().name
                println(field)
                val message = violation.message
                println(message)
                CustomFieldError.of(field, "", message)
            }

            return of(
                exception = GlobalErrorCode.BAD_REQUEST,
                fieldErrors = fieldErrors
            )
        }

        fun of(exception: MethodArgumentTypeMismatchException): ErrorResponse {
            val value = exception.value
            val fieldErrors = CustomFieldError.of(exception.name, value.toString(), exception.errorCode)

            return of(
                exception = GlobalErrorCode.BAD_REQUEST,
                fieldErrors = fieldErrors
            )
        }

        fun of(exception: DataIntegrityViolationException): ErrorResponse = of(
            exception = GlobalErrorCode.BAD_REQUEST,
            fieldErrors = CustomFieldError.of("", "", exception.message ?: "")
        )

        private fun of(exception: ErrorProperty, fieldErrors: List<CustomFieldError>) = ErrorResponse(
            status = exception.status(),
            message = exception.message(),
            fieldErrors = fieldErrors
        )
    }
}