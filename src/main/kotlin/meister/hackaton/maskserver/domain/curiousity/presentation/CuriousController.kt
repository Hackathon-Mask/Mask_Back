package meister.hackaton.maskserver.domain.curiousity.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.curiousity.service.AddCuriousityService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "궁금해요", description = "궁금해요 관련 API 입니다.")
@RequestMapping("/curiousity")
@RestController
class CuriousController(
    private val addCuriousityService: AddCuriousityService
) {

    @Operation(summary = "궁금해요 추가")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{question-id}")
    fun addCuriousity(
        @PathVariable("question-id") questionId: Long
    ) {
        addCuriousityService.execute(questionId)
    }

}