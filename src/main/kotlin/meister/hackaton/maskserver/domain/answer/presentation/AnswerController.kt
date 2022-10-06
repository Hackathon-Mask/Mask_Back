package meister.hackaton.maskserver.domain.answer.presentation

import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.answer.presentation.dto.CreateAnswerRequest
import meister.hackaton.maskserver.domain.answer.service.CreateAnswerService
import meister.hackaton.maskserver.domain.question.presentation.dto.QuestionDetailsResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "답변", description = "답변 관련 API 입니다.")
@RequestMapping("/answers")
@RestController
class AnswerController(
    private val answerService: CreateAnswerService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{question-id}")
    fun createAnswer(
        @PathVariable("question-id") questionId: Long,
        @RequestBody @Valid request: CreateAnswerRequest
    ): QuestionDetailsResponse.Answer {
        return answerService.execute(questionId, request)
    }

}