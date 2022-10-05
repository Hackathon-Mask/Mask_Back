package meister.hackaton.maskserver.thirdparty.image

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("cloud.aws.s3")
@ConstructorBinding
class S3Properties(
    val bucket: String
)