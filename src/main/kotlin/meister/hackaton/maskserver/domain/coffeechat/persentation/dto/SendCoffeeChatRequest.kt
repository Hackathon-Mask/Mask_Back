package meister.hackaton.maskserver.domain.coffeechat.persentation.dto

data class SendCoffeeChatRequest(
    val toUserId: Long,
    val message: String
)
