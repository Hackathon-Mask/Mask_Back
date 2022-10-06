package meister.hackaton.maskserver.domain.image.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import meister.hackaton.maskserver.domain.image.presentation.dto.ImageResponse
import meister.hackaton.maskserver.domain.image.service.ImageUploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Tag(name = "이미지", description = "이미지 관련 API 입니다.")
@RequestMapping("/images")
@RestController
class ImageController(
    private val imageUploadService: ImageUploadService
) {

    @Operation(summary = "이미지 업로드")
    @PostMapping
    fun upload(@RequestPart image: MultipartFile): ImageResponse {
        return imageUploadService.execute(image)
    }

}