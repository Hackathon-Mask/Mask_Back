package meister.hackaton.maskserver.domain.question.presentation.dto

import meister.hackaton.maskserver.domain.question.model.QuestionStatus
import meister.hackaton.maskserver.domain.tag.presentation.dto.TagResponse
import java.time.LocalDate

data class QuestionDetailsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val titleImage: String,
    val status: QuestionStatus,
    val majorTag: TagResponse.TagElement,
    val skills: List<TagResponse.TagElement>,
    val curiousityCount: Int,
    val answerCount: Int,
    val createdAt: LocalDate,
    val writer: QuestionWriter,
    val answers: List<Answer>
) {
    data class QuestionWriter(
        val id: Long,
        val name: String
    )

    data class Answer(
        val id: Long,
        val content: String,
        val createdAt: LocalDate,
        val writer: AnswerWriter
    )

    data class AnswerWriter(
        val id: Long,
        val name: String,
        val majorTag: TagResponse.TagElement,
        val skills: List<TagResponse.TagElement>
    )
}