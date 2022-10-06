package meister.hackaton.maskserver.thirdparty.message.coolsms

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("sms")
class CoolSmsProperties(
    val apiKey: String,
    val apiSecret: String
)