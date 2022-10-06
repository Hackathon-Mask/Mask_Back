package meister.hackaton.maskserver.domain.coffeechat.event

data class CoffeeChatArrivalEvent(
    val from: String,
    val to: String,
    val message: String
)
