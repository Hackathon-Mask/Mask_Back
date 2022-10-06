package meister.hackaton.maskserver.thirdparty.message.coolsms

import meister.hackaton.maskserver.thirdparty.message.MessageSender
import net.nurigo.java_sdk.api.Message
import org.springframework.stereotype.Component

@Component
class CoolSmsMessageSender(
    private val coolSmsProperties: CoolSmsProperties
) : MessageSender {

    override fun send(from: String, to: String, message: String) {
        val coolSmsMessage = Message(
            coolSmsProperties.apiKey,
            coolSmsProperties.apiSecret
        )

        val params = hashMapOf(
            "to" to to,
            "from" to from,
            "type" to "SMS",
            "text" to message,
            "app_version" to "app 1.0"
        )

        coolSmsMessage.send(params)
    }

}