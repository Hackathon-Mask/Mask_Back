package meister.hackaton.maskserver.domain.image.service

import meister.hackaton.maskserver.domain.image.presentation.dto.ImageResponse
import meister.hackaton.maskserver.thirdparty.image.ImageUploader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ImageUploadService(
    private val imageUploader: ImageUploader
) {

    @Transactional
    fun execute(image: MultipartFile): ImageResponse {
        return ImageResponse(
            imageUploader.upload(image)
        )
    }

}