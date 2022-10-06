package meister.hackaton.maskserver.thirdparty.image

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import meister.hackaton.maskserver.global.exception.FileIOInterruptedException
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Component
class S3Uploader(
    private val awsProperties: S3Properties,
    private val amazonS3Client: AmazonS3Client
) : ImageUploader {

    override fun upload(image: MultipartFile): String {
        val fileName = awsProperties.bucket + "/" + image.originalFilename

        inputS3(image, fileName)

        return getResource(fileName)
    }

    private fun inputS3(image: MultipartFile, fileName: String?) {
        try {
            val inputStream = image.inputStream

            val objectMetadata = ObjectMetadata().apply {
                this.contentLength = image.size
                this.contentType = image.contentType
            }

            amazonS3Client.putObject(
                PutObjectRequest(
                    awsProperties.bucket,
                    fileName,
                    inputStream,
                    objectMetadata
                ).withCannedAcl(CannedAccessControlList.PublicRead)
            )
        } catch (e: IOException) {
            throw FileIOInterruptedException.EXCEPTION
        }
    }

    private fun getResource(fileName: String?): String {
        return awsProperties.url + fileName
    }

}