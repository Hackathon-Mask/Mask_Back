package meister.hackaton.maskserver.global.error.base

interface ErrorProperty {

    fun status(): Int

    fun message(): String

}