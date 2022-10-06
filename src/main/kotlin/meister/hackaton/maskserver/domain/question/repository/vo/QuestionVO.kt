package meister.hackaton.maskserver.domain.question.repository.vo

import com.querydsl.core.annotations.QueryProjection
import meister.hackaton.maskserver.domain.question.model.QuestionStatus

data class QuestionVO @QueryProjection constructor(
    val id: Long,
    val title: String,
    val summary: String,
    val titleImage: String,
    val status: QuestionStatus,
    val majorTagId: Long,
    val majorTagName: String,
    val curiousityCount: Int
)