package meister.hackaton.maskserver.domain.tag.service

import meister.hackaton.maskserver.domain.tag.model.TagType
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import meister.hackaton.maskserver.domain.tag.repository.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMajorTagsService(
    private val tagRepository: TagRepository
) {

    @Transactional(readOnly = true)
    fun execute(): TagResponse {
        val result = tagRepository.findTagsByType(TagType.MAJOR).map {
            TagResponse.TagElement(it.id, it.name)
        }

        return TagResponse(result)
    }

}