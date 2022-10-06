package meister.hackaton.maskserver.domain.question.event

data class QuestionCreationEvent(
    val majorTag: String,
    val from: String,
    val to: List<String>
)
