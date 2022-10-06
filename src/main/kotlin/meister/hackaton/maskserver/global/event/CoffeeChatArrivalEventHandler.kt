package meister.hackaton.maskserver.global.event

import meister.hackaton.maskserver.domain.coffeechat.event.CoffeeChatArrivalEvent
import meister.hackaton.maskserver.thirdparty.message.MessageSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoffeeChatArrivalEventHandler(
    private val messageSender: MessageSender
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun sendMessage(event: CoffeeChatArrivalEvent) {
        println("CoffeeChatArrivalEvent activated")
        messageSender.send(
            from = event.from,
            to = event.to,
            message = event.message
        )
        println("${event.from} messaged to ${event.to} : ${event.message}")
    }

}