package meister.hackaton.maskserver.global.event

import meister.hackaton.maskserver.domain.question.event.QuestionCreationEvent
import meister.hackaton.maskserver.thirdparty.message.MessageSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class QuestionCreationEventHandler(
    private val messageSender: MessageSender
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun sendMessage(event: QuestionCreationEvent) {
        println("QuestionCreationEvent activated")

        event.to.forEach {
            messageSender.send(
                from = event.from,
                to = it,
                message = "${event.majorTag}에 관한 새로운 질문이 올라왔어요!"
            )
        }

        println("${event.from} messaged to ${event.to} : ${event.majorTag}")
    }

}