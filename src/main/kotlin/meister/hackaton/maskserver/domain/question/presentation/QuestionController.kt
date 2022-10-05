package meister.hackaton.maskserver.domain.question.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.question.presentation.dto.CreateQuestionRequest
import meister.hackaton.maskserver.domain.question.service.CreateQuestionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "질문", description = "질문 관련 API 입니다.")
@RequestMapping("/questions")
@RestController
class QuestionController(
    private val createQuestionService: CreateQuestionService
) {

    @Operation(summary = "질문 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createQuestion(@RequestBody @Valid request: CreateQuestionRequest) {
        createQuestionService.execute(request)
    }

}