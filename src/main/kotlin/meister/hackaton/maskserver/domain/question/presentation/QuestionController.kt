package meister.hackaton.maskserver.domain.question.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.question.model.QueryType
import meister.hackaton.maskserver.domain.question.presentation.dto.CreateQuestionRequest
import meister.hackaton.maskserver.domain.question.presentation.dto.QuestionDetailsResponse
import meister.hackaton.maskserver.domain.question.presentation.dto.QuestionResponse
import meister.hackaton.maskserver.domain.question.service.CreateQuestionService
import meister.hackaton.maskserver.domain.question.service.QueryMyQuestionsService
import meister.hackaton.maskserver.domain.question.service.QueryQuestionService
import meister.hackaton.maskserver.domain.question.service.QueryQuestionsService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "질문", description = "질문 관련 API 입니다.")
@RequestMapping("/questions")
@RestController
class QuestionController(
    private val createQuestionService: CreateQuestionService,
    private val queryQuestionsService: QueryQuestionsService,
    private val queryQuestionService: QueryQuestionService,
    private val queryMyQuestionsService: QueryMyQuestionsService
) {

    @Operation(summary = "질문 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createQuestion(@RequestBody @Valid request: CreateQuestionRequest) {
        createQuestionService.execute(request)
    }

    @Operation(summary = "질문 목록 조회")
    @GetMapping
    fun getQuestions(
        @RequestParam(required = false) keyword: String?,
        @RequestParam majorTagId: Long,
        @RequestParam queryType: QueryType
    ): QuestionResponse {
        return queryQuestionsService.execute(keyword, majorTagId, queryType)
    }

    @Operation(summary = "질문 단일 조회")
    @GetMapping("/{question-id}")
    fun getQuestion(
        @PathVariable("question-id") questionId: Long
    ): QuestionDetailsResponse {
        return queryQuestionService.execute(questionId)
    }

    @Operation(summary = "자신이 올린 질문 목록 조회")
    @GetMapping("/me")
    fun getMyQuestions(): QuestionResponse {
        return queryMyQuestionsService.execute()
    }

}