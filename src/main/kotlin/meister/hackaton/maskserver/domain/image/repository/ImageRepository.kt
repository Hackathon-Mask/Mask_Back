package meister.hackaton.maskserver.domain.image.repository

import meister.hackaton.maskserver.domain.image.model.Image
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : CrudRepository<Image, Long> {
}