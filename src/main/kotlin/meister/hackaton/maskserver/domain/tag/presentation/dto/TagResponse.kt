package meister.hackaton.maskserver.domain.tag.presentation.dto

data class TagResponse(
    val tags: List<TagElement>
) {
    data class TagElement(
        val id: Long,
        val name: String
    )
}