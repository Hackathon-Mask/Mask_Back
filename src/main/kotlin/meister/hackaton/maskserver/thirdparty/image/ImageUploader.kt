package meister.hackaton.maskserver.thirdparty.image

import org.springframework.web.multipart.MultipartFile

interface ImageUploader {

    fun upload(image: MultipartFile): String

}