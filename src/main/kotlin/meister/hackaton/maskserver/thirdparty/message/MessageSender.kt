package meister.hackaton.maskserver.thirdparty.message

interface MessageSender {

    fun send(from: String, to: String, message: String)

}