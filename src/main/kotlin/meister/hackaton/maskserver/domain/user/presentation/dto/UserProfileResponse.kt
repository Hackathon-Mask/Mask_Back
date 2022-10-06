package meister.hackaton.maskserver.domain.user.presentation.dto

import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse

data class UserProfileResponse(
    val userId: Long,
    val name: String,
    val school: String,
    val generation: Int,
    val belong: String,
    val email: String,
    val majorTag: TagResponse.TagElement,
    val skills: List<TagResponse.TagElement>,
)