package meister.hackaton.maskserver.domain.question.presentation.dto

import meister.hackaton.maskserver.domain.question.model.QuestionStatus
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse

data class QuestionResponse(
    val questions: List<QuestionElement>
) {
    data class QuestionElement(
        val id: Long,
        val title: String,
        val summary: String,
        val titleImage: String,
        val status: QuestionStatus,
        val majorTag: TagResponse.TagElement,
        val curiousityCount: Int
    )
}