package org.sopt.certi.presentation.type

import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.domain.type.GradeType
import org.sopt.certi.domain.type.TrackType

enum class SelectableButtonType(
    val options: List<String>,
    val verticalPadding: Int = 18,
    val chunkedSize: Int = 2
) {
    CATEGORY(
        options = listOf(
            CategoryType.BUSINESS.description,
            CategoryType.MARKETING.description,
            CategoryType.TRADE.description,
            CategoryType.IT.description,
            CategoryType.MANUFACTURE.description,
            CategoryType.SALES.description,
            CategoryType.CONSTRUCTION.description,
            CategoryType.FINANCE.description,
            CategoryType.RND.description,
            CategoryType.DESIGN.description,
            CategoryType.MEDIA.description,
            CategoryType.PROFESSIONAL.description
        )
    ),
    GRADE(
        options = listOf(
            GradeType.FRESHMAN.description,
            GradeType.SOPHOMORE.description,
            GradeType.JUNIOR.description,
            GradeType.SENIOR.description,
            GradeType.GRADUATE.description
        ),
        chunkedSize = 1
    ),
    TRACK(
        options = listOf(
            TrackType.HUMANITIES.description,
            TrackType.SOCIAL.description,
            TrackType.EDUCATION.description,
            TrackType.NATURAL.description,
            TrackType.ENGINEERING.description,
            TrackType.MEDICAL.description,
            TrackType.ARTS.description
        ),
        verticalPadding = 30
    )
}
