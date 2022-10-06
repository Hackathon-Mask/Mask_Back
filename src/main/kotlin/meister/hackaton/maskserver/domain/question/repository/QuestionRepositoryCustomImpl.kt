package meister.hackaton.maskserver.domain.question.repository

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import meister.hackaton.maskserver.domain.question.model.QueryType
import meister.hackaton.maskserver.domain.question.repository.vo.QQuestionVO
import meister.hackaton.maskserver.domain.question.repository.vo.QuestionVO
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import meister.hackaton.maskserver.domain.question.model.QQuestion.question as questionEntity
import meister.hackaton.maskserver.domain.tag.model.QTag.tag as tagEntity

@Component
class QuestionRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : QuestionRepositoryCustom {
    override fun findByQueryType(
        keyword: String?,
        majorTag: Long,
        type: QueryType
    ): List<QuestionVO> {

        return queryFactory
            .select(
                QQuestionVO(
                    questionEntity.id,
                    questionEntity.title,
                    questionEntity.summary,
                    questionEntity.titleImage,
                    questionEntity.status,
                    tagEntity.id,
                    tagEntity.name,
                    questionEntity.curiousityCount
                )
            )
            .from(questionEntity)
            .leftJoin(questionEntity.majorTag, tagEntity)
            .where(
                keywordContains(keyword)
            )
//            .orderBy(sortByDate(type))
            .fetch()
    }

    private fun keywordContains(keyword: String?): BooleanExpression? =
        if (keyword == null) null else questionEntity.title.like(keyword)


//    private fun sortByDate(type: QueryType): OrderSpecifier<LocalDateTime> {
//        return when (type) {
//            QueryType.DATE -> questionEntity.createdAt.desc()
//            QueryType.POPULAR -> (questionEntity.curiousityCount.add(questionEntity.answerCount)).desc()
//        }
//    }
}