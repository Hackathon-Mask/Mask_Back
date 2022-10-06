package meister.hackaton.maskserver.domain.coffeechat.service

import meister.hackaton.maskserver.domain.coffeechat.event.CoffeeChatArrivalEvent
import meister.hackaton.maskserver.domain.coffeechat.persentation.dto.SendCoffeeChatRequest
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.util.SecurityUtil
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SendCoffeeChatService(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil,
    private val eventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun execute(request: SendCoffeeChatRequest) {
        val from = userRepository.findUserById(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException.EXCEPTION
        val to = userRepository.findUserById(request.toUserId) ?: throw UserNotFoundException.EXCEPTION

        eventPublisher.publishEvent(CoffeeChatArrivalEvent(from.phoneNumber, to.phoneNumber, request.message))
    }

}