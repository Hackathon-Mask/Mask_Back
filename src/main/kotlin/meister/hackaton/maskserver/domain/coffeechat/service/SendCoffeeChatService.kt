package meister.hackaton.maskserver.domain.coffeechat.service

import meister.hackaton.maskserver.domain.coffeechat.persentation.dto.SendCoffeeChatRequest
import meister.hackaton.maskserver.domain.user.exception.UserNotFoundException
import meister.hackaton.maskserver.domain.user.repositiory.UserRepository
import meister.hackaton.maskserver.global.util.SecurityUtil
import meister.hackaton.maskserver.thirdparty.message.MessageSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SendCoffeeChatService(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil,
    private val messageSender: MessageSender
) {

    @Transactional
    fun execute(request: SendCoffeeChatRequest) {
        val from = userRepository.findUserById(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException.EXCEPTION
        val to = userRepository.findUserById(request.toUserId) ?: throw UserNotFoundException.EXCEPTION

        messageSender.send(from.phoneNumber, to.phoneNumber, request.message)
    }

}