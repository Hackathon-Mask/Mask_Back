package meister.hackaton.maskserver.domain.tag.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.domain.tag.service.QueryMajorTagsService
import meister.hackaton.maskserver.domain.tag.service.QuerySkillsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "태그", description = "태그 관련 API 입니다.")
@RequestMapping("/tags")
@RestController
class TagController(
    private val queryMajorTagsService: QueryMajorTagsService,
    private val querySkillsService: QuerySkillsService
) {

    @Operation(summary = "전공 태그 조회")
    @GetMapping("/majors")
    fun getMajorTags(): TagResponse {
        return queryMajorTagsService.execute()
    }

    @Operation(summary = "기술 스택 조회")
    @GetMapping("/skills")
    fun getSkills(): TagResponse {
        return querySkillsService.execute()
    }
    
}