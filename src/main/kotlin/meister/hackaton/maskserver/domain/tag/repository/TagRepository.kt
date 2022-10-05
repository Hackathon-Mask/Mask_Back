package meister.hackaton.maskserver.domain.tag.repository

import meister.hackaton.maskserver.domain.tag.model.Tag
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : CrudRepository<Tag, Long> {
}