package meister.hackaton.maskserver.domain.tag.repository

import meister.hackaton.maskserver.domain.tag.model.Tag
import meister.hackaton.maskserver.domain.tag.model.TagType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : CrudRepository<Tag, Long> {

    fun findTagById(id: Long): Tag?

    fun findTagsByType(type: TagType): List<Tag>

}