package meister.hackaton.maskserver.domain.question.presentation.dto

import javax.validation.constraints.NotNull

data class CreateQuestionRequest(

    @field:NotNull
    val title: String,

    @field:NotNull
    val content: String,

    @field:NotNull
    val majorTag: Long,

    @field:NotNull
    val skills: List<Long>,

    @field:NotNull
    val titleImage: String,

    @field:NotNull
    val summary: String

)