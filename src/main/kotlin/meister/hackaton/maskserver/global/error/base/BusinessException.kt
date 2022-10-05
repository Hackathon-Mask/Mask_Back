package meister.hackaton.maskserver.global.error.base

abstract class BusinessException(
    val exceptionProperty: ErrorProperty
) : RuntimeException()