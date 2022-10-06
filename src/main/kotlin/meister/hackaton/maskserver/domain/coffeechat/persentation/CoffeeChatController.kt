package meister.hackaton.maskserver.domain.coffeechat.persentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.coffeechat.persentation.dto.SendCoffeeChatRequest
import meister.hackaton.maskserver.domain.coffeechat.service.SendCoffeeChatService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "커피챗", description = "커피챗 관련 API 입니다.")
@RequestMapping("/coffee-chat")
@RestController
class CoffeeChatController(
    private val sendCoffeeChatService: SendCoffeeChatService
) {

    @Operation(summary = "커피챗 전송")
    @PostMapping
    fun sendCoffeeChat(@RequestBody @Valid request: SendCoffeeChatRequest) {
        sendCoffeeChatService.execute(request)
    }

}